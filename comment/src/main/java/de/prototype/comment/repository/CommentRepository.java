package de.prototype.comment.repository;

import de.prototype.comment.model.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;



@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, String> {
    Flux<Comment> findCommentsByRentalId(int rentalId);
}
