package de.prototype.comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "comments")
@CompoundIndex(name = "rental-rec-id", unique = true, def = "{'rentalId': 1, 'commentId' : 1}")
public class Comment {

    @Id
    private String id;

    private int rentalId;
    private int commentId;
    private String username;
    private String content;
}
