package de.prototype.comment.mapper;

import de.prototype.comment.dto.CommentDto;
import de.prototype.comment.model.Comment;

public class CommentMapper {

    public static CommentDto toDto(Comment comment){
        return new CommentDto(comment.getId(), comment.getRentalId(), comment.getCommentId(), comment.getUsername(), comment.getContent());
    }

    public static Comment toEntity(CommentDto commentDto){
        return new Comment (commentDto.getId(), commentDto.getRentalId(), commentDto.getCommentId(), commentDto.getUsername(), commentDto.getContent());
    }
}
