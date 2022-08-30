package de.prototype.comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("comments")
public class Comment {

    @Id
    private String id;

    private int rentalId;
    private int commentId;
    private String username;
    private String content;
}
