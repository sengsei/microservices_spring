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

    @GetMapping("/{rentalId}")
    public Mono<RentalDto> findById(@PathVariable int rentalId){
        return rentalService.findById(rentalId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<RentalDto>> save(@RequestBody Mono<RentalDto> rentalDtoMono) {
        return rentalService.save(rentalDtoMono).map(ResponseEntity::ok);
    }

    @PutMapping("/{rentalId}")
    public Mono<ResponseEntity<RentalDto>> update(@PathVariable("rentalId") int rentalId, @RequestBody Mono<RentalDto> rentalDtoMono) {
        return rentalService.update(rentalId, rentalDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{rentalId}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable("rentalId") int rentalId){
        return rentalService.delete(rentalId)
                .map(rental -> ResponseEntity.noContent().build())
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
