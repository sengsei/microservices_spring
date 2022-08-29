package de.prototype.rental;


import de.prototype.rental.model.Rental;
import de.prototype.rental.repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalApplicationTests {

    @Autowired
    RentalRepository repository;

    @Autowired
    private WebTestClient client;

    @Test
    void findAllRentals() {
        client.get()
                .uri("/api/rentals")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Rental.class);

    }

    @Test
    void shouldFindRentalById() {
        Rental rental = new Rental();
        rental.setRentalId("42");
        rental.setName("Trekking Bike");
        rental.setDescription("Reifen abgefahren");

        repository.save(rental).block();

        client.get()
                .uri("/api/rentals/{id}", "42")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Trekking Bike")
                .jsonPath("$.id").isEqualTo("42")
                .jsonPath("$.description").isEqualTo("Reifen abgefahren");
    }

    @Test
    void shouldCreateOneRental() {
        Rental rental = new Rental();
        rental.setName("Trekking Bike");
        rental.setDescription("Reifen abgefahren");

        client.post()
                .uri("/api/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(rental), Rental.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Trekking Bike")
                .jsonPath("$.description").isEqualTo("Reifen abgefahren");

    }

    @Test
    void shouldChangeExistingRental() {
        Rental rental1 = new Rental();
        rental1.setRentalId("42");
        rental1.setName("Trekking Bike");
        rental1.setDescription("Reifen abgefahren");

        repository.save(rental1).block();

        Rental rental2 = new Rental();
        rental2.setRentalId("42");
        rental2.setName("Trekking Bike");
        rental2.setDescription("Reifen in Ordnung");

        client.put()
                .uri("/api/rentals/{id}", Collections.singletonMap("id", rental1.getRentalId()))
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
        Rental rental = repository.save(new Rental("42", "Trekking Bike", "kaputt")).block();

        assert rental != null;
        repository.save(rental).block();

        client.delete()
                .uri("/api/rentals/{id}", Collections.singletonMap("id", rental.getRentalId()))
                .exchange()
                .expectStatus().isNoContent();
    }


}
