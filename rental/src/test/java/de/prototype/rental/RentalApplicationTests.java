package de.prototype.rental;


import de.prototype.rental.model.Rental;
import de.prototype.rental.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalApplicationTests {

    @Autowired
    RentalRepository repository;

    @Autowired
    private WebTestClient client;

    @BeforeEach
    void cleanDb() {
        repository.deleteAll().block();
    }

    @Test
    void findAllRentals() {
        Rental rental = new Rental("", 1, "Trekking", "keine Luft auf Reifen");

        repository.save(rental).block();

        client.get()
                .uri("/api/rentals")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Rental.class);

        assertEquals(1, (long)repository.count().block());
    }

    @Test
    void shouldFindRentalById() {
        Rental rental = new Rental("", 1, "Trekking", "keine Luft auf Reifen");

        repository.save(rental).block();

        client.get()
                .uri("/api/rentals/{rentalId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.rentalId").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Trekking")
                .jsonPath("$.description").isEqualTo("keine Luft auf Reifen");
    }

    @Test
    void shouldCreateOneRental() {
        Rental rental = new Rental("", 1, "Trekking", "keine Luft auf Reifen");

        repository.save(rental);

        client.post()
                .uri("/api/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(rental), Rental.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.rentalId").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Trekking")
                .jsonPath("$.description").isEqualTo("keine Luft auf Reifen");

    }

    @Test
    void shouldChangeExistingRental() {
        Rental rental1 = new Rental("", 1, "Trekking Bike", "Reifen abgefahren");

        repository.save(rental1).block();

        Rental rental2 = new Rental("", 1, "Trekking Bike", "Reifen in Ordnung");

        client.put()
                .uri("/api/rentals/{rentalId}", Collections.singletonMap("rentalId", rental1.getRentalId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(rental2), Rental.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.description").isEqualTo("Reifen in Ordnung");
    }

    @Test
    void shouldDeleteRentalById() {
        Rental rental = repository.save(new Rental("", 1, "Trekking Bike", "kaputt")).block();

        assert rental != null;
        repository.save(rental).block();

        client.delete()
                .uri("/api/rentals/{rentalId}", Collections.singletonMap("rentalId", rental.getRentalId()))
                .exchange()
                .expectStatus().isNoContent();
    }


}
