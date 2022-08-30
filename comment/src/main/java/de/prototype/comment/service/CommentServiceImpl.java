package de.prototype.comment.service;

import de.prototype.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Flux<CommentDto> findAll() {
        return null;
    }

    @Override
    public Mono<CommentDto> findById(int rentalId) {
        return null;
    }

    @Override
    public Mono<CommentDto> save(Mono<CommentDto> commentDtoMono) {
        return null;
    }

    @Override
    public Mono<CommentDto> update(int rentalId, Mono<CommentDto> commentDtoMono) {
        return null;
    }

    @Override
    public Mono<Void> delete(int rentalId) {
        return null;
    }
}
