package de.prototype.rental;


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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RentalApplication.class)
@Import(RentalServiceImpl.class)
class RentalApplicationTests {

	@MockBean
	RentalServiceImpl service;

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

		Mockito.verify(repository, Mockito.times(1)).findAll();

	}



}
