package de.prototype.rental.controller;

import de.prototype.rental.dto.RentalDto;
import de.prototype.rental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class RentalController {

    private RentalService rentalService;

    @GetMapping("/api/rentals")
    public Flux<RentalDto> findAll() {
        return rentalService.findAll();
    }

    @PostMapping("/api/rentals")
    public Mono<ResponseEntity<RentalDto>> save(@RequestBody Mono<RentalDto> rentalDtoMono) {
        return rentalService.save(rentalDtoMono).map(ResponseEntity::ok);
    }
}
