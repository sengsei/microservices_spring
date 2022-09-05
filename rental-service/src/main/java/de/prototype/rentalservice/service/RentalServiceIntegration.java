package de.prototype.rentalservice.service;


import de.prototype.rentalservice.model.Comment;
import de.prototype.rentalservice.model.Rental;
import de.prototype.rentalservice.model.RentalAndComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class RentalServiceIntegration {

    private final WebClient client;
    private final String rentalServiceUrl;
    private final String commentServiceUrl;


    @Autowired
    public RentalServiceIntegration(

            @Value("localhost") String rentalServiceHost,
            @Value("7001") int rentalServicePort,

            @Value("localhost") String commentServiceHost,
            @Value("7002") int commentServicePort

    ) {
        this.client = WebClient.builder().build();
        this.rentalServiceUrl = "http://" + rentalServiceHost + ":" + rentalServicePort;
        this.commentServiceUrl = "http://" + commentServiceHost + ":" + commentServicePort;
    }


    public Flux<Comment> getComments(int rentalId) {
        String url = commentServiceUrl + "/api/comments/" + rentalId;
        return client.get().uri(url).retrieve().bodyToFlux(Comment.class);
    }

    public Mono<Rental> getRental(int rentalId){
        String url = rentalServiceUrl + "/api/rentals/" + rentalId;
        return client.get().uri(url).retrieve().bodyToMono(Rental.class);
    }

    public Mono<Rental> createRental(Rental rental){
        String url = rentalServiceUrl + "/api/rentals";
        return client.post().uri(url)
                 .body(Mono.just(rental), Rental.class)
                .retrieve().bodyToMono(Rental.class);
    }

    public Mono<Comment> createComment(Comment comment){
        String url = commentServiceUrl + "/api/comments";
        return client.post().uri(url)
                .body(Mono.just(comment),Comment.class)
                .retrieve().bodyToMono(Comment.class);
    }


}
