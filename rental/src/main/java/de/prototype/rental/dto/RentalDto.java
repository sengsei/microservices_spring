package de.prototype.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalDto {

    private String id;
    private int rentalId;
    private String name;
    private String description;
}
