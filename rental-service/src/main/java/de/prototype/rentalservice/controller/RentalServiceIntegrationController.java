package de.prototype.rentalservice.controller;

import de.prototype.rentalservice.model.Comment;
import de.prototype.rentalservice.model.CommentSummary;
import de.prototype.rentalservice.model.Rental;
import de.prototype.rentalservice.model.RentalAndComment;
import de.prototype.rentalservice.service.RentalServiceIntegration;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class RentalServiceIntegrationController {

    private final RentalServiceIntegration integration;

    private static final Logger LOG = LoggerFactory.getLogger(RentalServiceIntegrationController.class);

    @GetMapping(
            value = "/api/rentals-and-comments/{rentalId}",
            produces = "application/json")
    public Mono<RentalAndComment> getRental(@PathVariable int rentalId) {
        return Mono.zip(
                        values -> createRentalAndComment((Rental) values[0], (List<Comment>) values[1]),
                        integration.getRental(rentalId),
                        integration.getComments(rentalId).collectList())
                .doOnError(ex -> LOG.warn("failed: {}", ex.toString()));

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
