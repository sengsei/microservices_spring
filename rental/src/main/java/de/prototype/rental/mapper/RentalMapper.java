package de.prototype.rental.mapper;

import de.prototype.rental.dto.RentalDto;
import de.prototype.rental.model.Rental;

public class RentalMapper {

    public static RentalDto toDto(Rental rental){
        return new RentalDto(rental.getId(), rental.getName(), rental.getDescription());
    }

    public static Rental toEntity(RentalDto rentalDto) {
        return new Rental(rentalDto.getId(), rentalDto.getName(), rentalDto.getDescription());
    }
}
