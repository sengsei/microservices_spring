package de.prototype.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {

    private String id;
    private int rentalId;
    private int commentId;
    private String username;
    private String content;
}
