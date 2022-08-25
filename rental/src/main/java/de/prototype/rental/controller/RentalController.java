package de.prototype.rental.controller;

import de.prototype.rental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RentalController {

    private RentalService rentalService;
}
