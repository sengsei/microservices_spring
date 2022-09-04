package de.prototype.rentalservice.controller;

import de.prototype.rentalservice.model.Comment;
import de.prototype.rentalservice.model.CommentSummary;
import de.prototype.rentalservice.model.Rental;
import de.prototype.rentalservice.model.RentalAndComment;
import de.prototype.rentalservice.service.RentalServiceIntegration;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals-and-comments")
public class RentalServiceIntegrationController {

    private final RentalServiceIntegration integration;

    @GetMapping("/{rentalId}")
    public Mono<RentalAndComment> getRental(@PathVariable int rentalId) {
        return Mono.zip(
                        values -> createRentalAndComment((Rental) values[0], (List<Comment>) values[1]),
                        integration.getRental(rentalId),
                        integration.getComments(rentalId).collectList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createRental(@RequestBody RentalAndComment rentalAndComment){
        List<Mono> monos = new ArrayList<>();
        Rental rental = new Rental(rentalAndComment.getRentalId(), rentalAndComment.getName(), rentalAndComment.getDescription());
        monos.add(integration.createRental(rental));

        if (rentalAndComment.getComments() != null){
            rentalAndComment.getComments().forEach(
                    com -> {
                        Comment comment = new Comment(rentalAndComment.getRentalId(), com.getCommentId(), com.getUsername(), com.getContent());
                        monos.add(integration.createComment(comment));
                    }
            );
        }
        return Mono.zip(e -> "", monos.toArray(new Mono[0])).then();
    }


    private RentalAndComment createRentalAndComment(Rental rental, List<Comment> comments) {

        int rentalId = rental.getRentalId();
        String name = rental.getName();
        String description = rental.getDescription();

        List<CommentSummary> commentSummaries = comments.stream()
                .map(c -> new CommentSummary(c.getCommentId(), c.getUsername(), c.getContent())).collect(Collectors.toList());

        return new RentalAndComment(rentalId, name, description, commentSummaries);
    }
}
