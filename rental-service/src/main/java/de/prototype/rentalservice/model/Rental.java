package de.prototype.rentalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rental {

    private int rentalId;
    private String name;
    private String description;
}
