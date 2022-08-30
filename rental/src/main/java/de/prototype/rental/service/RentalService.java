package de.prototype.rental.service;

import de.prototype.rental.dto.RentalDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RentalService {
    Flux<RentalDto> findAll();
    Mono<RentalDto> findById(int rentalId);
    Mono<RentalDto> save(Mono<RentalDto> rentalDtoMono);
    Mono<RentalDto> update(int rentalId, Mono<RentalDto> rentalDtoMono);
    Mono<Void> delete(int rentalId);
}
