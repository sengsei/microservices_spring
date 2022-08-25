package de.prototype.rental.repository;

import de.prototype.rental.model.Rental;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends ReactiveCrudRepository<Rental, String> {
}
