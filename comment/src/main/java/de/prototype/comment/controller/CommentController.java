package de.prototype.comment.controller;

import de.prototype.comment.dto.CommentDto;
import de.prototype.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    @GetMapping("/{rentalId}")
    public Flux<CommentDto> findAllCommentsByRentalId(@PathVariable int rentalId){
        return commentService.findAllCommentsByRentalId(rentalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<CommentDto>> save(@RequestBody Mono<CommentDto> commentDtoMono){
        return commentService.save(commentDtoMono).map(ResponseEntity::ok);
    }
}
