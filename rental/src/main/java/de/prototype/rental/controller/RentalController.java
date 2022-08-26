package de.prototype.rental.controller;

import de.prototype.rental.dto.RentalDto;
import de.prototype.rental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;

    @GetMapping()
    public Flux<RentalDto> findAll() {
        return rentalService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<RentalDto>> save(@RequestBody Mono<RentalDto> rentalDtoMono) {
        return rentalService.save(rentalDtoMono).map(ResponseEntity::ok);
    }
}
