package de.prototype.rental.service;

import de.prototype.rental.dto.RentalDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RentalServiceImpl implements RentalService {

    @Override
    public Flux<RentalDto> findAll() {
        return null;
    }

    @Override
    public Mono<RentalDto> findById(String id) {
        return null;
    }

    @Override
    public Mono<RentalDto> save(Mono<RentalDto> rentalDtoMono) {
        return null;
    }

    @Override
    public Mono<RentalDto> update(String id, Mono<RentalDto> rentalDtoMono) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
