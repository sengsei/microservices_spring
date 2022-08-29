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
    public Mono<RentalDto> findById(String id) {
        return rentalRepository.findById(id).map(RentalMapper::toDto);
    }

    @Override
    public Mono<RentalDto> save(Mono<RentalDto> rentalDtoMono) {
        return rentalDtoMono.map(RentalMapper::toEntity).flatMap(rentalRepository::save).map(RentalMapper::toDto);
    }

    @Override
    public Mono<RentalDto> update(String id, Mono<RentalDto> rentalDtoMono) {
        return rentalRepository.findById(id)
                .flatMap(rental -> rentalDtoMono.map(RentalMapper::toEntity))
                .doOnNext(rental -> rental.setId(id))
                .flatMap(rentalRepository::save)
                .map(RentalMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return rentalRepository.deleteById(id);
    }
}
