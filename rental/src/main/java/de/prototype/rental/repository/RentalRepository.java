package de.prototype.rental.repository;

import de.prototype.rental.model.Rental;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RentalRepository extends ReactiveCrudRepository<Rental, String> {
    Mono<Rental> findByRentalId(int rentalId);
    Mono<Void> deleteByRentalId(int rentalId);
}
