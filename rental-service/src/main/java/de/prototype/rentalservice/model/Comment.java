package de.prototype.rentalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    private int rentalId;
    private int commentId;
    private String username;
    private String content;
}
