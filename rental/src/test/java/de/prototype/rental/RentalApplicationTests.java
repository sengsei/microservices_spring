package de.prototype.rental;


import de.prototype.rental.controller.RentalController;
import de.prototype.rental.model.Rental;
import de.prototype.rental.repository.RentalRepository;
import de.prototype.rental.service.RentalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RentalController.class)
@Import(RentalServiceImpl.class)
class RentalApplicationTests {

	@MockBean
	RentalRepository repository;

	@Autowired
	private WebTestClient client;

	@Test
	void findAllRentals() {
		Rental rental1 = new Rental();
		rental1.setName("Trekking Bike");
		rental1.setDescription("Reifen abgefahren");

		Rental rental2 = new Rental();
		rental2.setName("Rennrad");
		rental2.setDescription("keine Luftpumpe dabei");

		Mockito.when(repository.findAll()).thenReturn(Flux.just(rental1, rental2));

		client.get()
				.uri("/api/rentals")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Rental.class);

		Mockito.verify(repository, times(1)).findAll();

	}

	@Test
	void shouldCreateOneRental(){
		Rental rental1 = new Rental();
		rental1.setName("Trekking Bike");
		rental1.setDescription("Reifen abgefahren");

		Mockito.when(repository.save(rental1)).thenReturn(Mono.just(rental1));

		client.post()
				.uri("/api/rentals")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(rental1))
				.exchange()
				.expectStatus().isOk();

		Mockito.verify(repository, times(1)).save(rental1);

	}



}
