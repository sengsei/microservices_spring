package de.prototype.rental.service;

import de.prototype.rental.dto.RentalDto;
import de.prototype.rental.mapper.RentalMapper;
import de.prototype.rental.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    @Override
    public Flux<RentalDto> findAll() {
        return rentalRepository.findAll().map(RentalMapper::toDto);
    }

    @Override
    public Mono<RentalDto> findById(int rentalId) {
        return rentalRepository.findByRentalId(rentalId).map(RentalMapper::toDto);
    }

    @Override
    public Mono<RentalDto> save(Mono<RentalDto> rentalDtoMono) {
        return rentalDtoMono.map(RentalMapper::toEntity).flatMap(rentalRepository::save).map(RentalMapper::toDto);
    }

    @Override
    public Mono<RentalDto> update(int rentalId, Mono<RentalDto> rentalDtoMono) {
        return rentalRepository.findByRentalId(rentalId)
                .flatMap(rental -> rentalDtoMono.map(RentalMapper::toEntity))
                .doOnNext(rental -> rental.setRentalId(rentalId))
                .flatMap(rentalRepository::save)
                .map(RentalMapper::toDto);
    }

    @Override
    public Mono<Void> delete(int rentalId) {
        return rentalRepository.deleteByRentalId(rentalId);
    }
}
