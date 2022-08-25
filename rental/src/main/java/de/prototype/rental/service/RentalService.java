package de.prototype.rental.service;

import de.prototype.rental.dto.RentalDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RentalService {
    Flux<RentalDto> findAll();
    Mono<RentalDto> findById(String id);
    Mono<RentalDto> save(Mono<RentalDto> rentalDtoMono);
    Mono<RentalDto> update(String id, Mono<RentalDto> rentalDtoMono);
    Mono<Void> delete(String id);
}
