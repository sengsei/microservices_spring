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
public class Comment {

    @Id
    private String id;

    private int rentalId;
    private int commentId;
    private String username;
    private String content;
}
