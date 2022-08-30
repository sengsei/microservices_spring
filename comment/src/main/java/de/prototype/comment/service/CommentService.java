package de.prototype.comment.service;

import de.prototype.comment.dto.CommentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Flux<CommentDto> findAll();
    Mono<CommentDto> findById(int rentalId);
    Mono<CommentDto> save(Mono<CommentDto> commentDtoMono);
    Mono<CommentDto> update(int rentalId, Mono<CommentDto> commentDtoMono);
    Mono<Void> delete(int rentalId);
}
