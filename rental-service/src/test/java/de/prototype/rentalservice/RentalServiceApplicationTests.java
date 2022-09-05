package de.prototype.rentalservice;

import de.prototype.rentalservice.model.Comment;
import de.prototype.rentalservice.model.Rental;
import de.prototype.rentalservice.service.RentalServiceIntegration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RentalServiceApplicationTests {

    @Autowired
    private WebTestClient client;

    @MockBean
    private RentalServiceIntegration integration;

    @BeforeEach
    void init() {

        when(integration.getRental(1))
                .thenReturn(Mono.just(new Rental(1, "Trekking Bike", "Reifen platt")));
        when(integration.getComments(1))
                .thenReturn(Flux.fromIterable(singletonList(new Comment(1, 1, "Alice", "Alice Content"))));

    }

    @Test
    void shouldGetRentalById() {
        Rental rental = new Rental(1, "Trekking Bike", "Reifen platt");

        client.get().uri("/api/rentals-and-comments/" + rental.getRentalId())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.rentalId").isEqualTo(rental.getRentalId())
                .jsonPath("$.comments.length()").isEqualTo(1);
    }

}


