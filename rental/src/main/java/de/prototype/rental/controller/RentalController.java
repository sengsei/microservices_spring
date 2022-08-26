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

    @GetMapping("/{id}")
    public Mono<RentalDto> findById(@PathVariable String id){
        return rentalService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<RentalDto>> save(@RequestBody Mono<RentalDto> rentalDtoMono) {
        return rentalService.save(rentalDtoMono).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<RentalDto>> update(@PathVariable("id") String id, @RequestBody Mono<RentalDto> heroDtoMono) {
        return rentalService.update(id, heroDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
