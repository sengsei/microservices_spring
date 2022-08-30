package de.prototype.comment.service;

import de.prototype.comment.dto.CommentDto;
import de.prototype.comment.mapper.CommentMapper;
import de.prototype.comment.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Override
    public Flux<CommentDto> findAllCommentsByRentalId(int rentalId) {
        return commentRepository.findByRentalId(rentalId).map(CommentMapper::toDto);
    }

    @Override
    public Mono<CommentDto> save(Mono<CommentDto> commentDtoMono) {
        return commentDtoMono.map(CommentMapper::toEntity).flatMap(commentRepository::save).map(CommentMapper::toDto);
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
