package de.prototype.comment;

import de.prototype.comment.model.Comment;
import de.prototype.comment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentApplicationTests {

    @Autowired
    CommentRepository repository;

    @Autowired
    private WebTestClient client;

    @BeforeEach
    void cleanDb(){
        repository.deleteAll().block();
    }

    @Test
    void shouldFindAllCommentsByRentalId() {
        Comment comment = new Comment("", 1, 1, "Foo", "Der Fahrradverleih ist super!");

        repository.save(comment).block();

        client.get()
                .uri("/api/comments/{rentalId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Comment.class);

        assertEquals(1, (long)repository.count().block());
    }

    @Test
    void shouldCreateOneComment(){
        Comment comment = new Comment("", 1, 1, "Foo", "Der Fahrradverleih ist super!");
        repository.save(comment);

        client.post()
                .uri("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(comment), Comment.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.rentalId").isEqualTo(1)
                .jsonPath("$.commentId").isEqualTo(1)
                .jsonPath("$.username").isEqualTo("Foo")
                .jsonPath("$.content").isEqualTo("Der Fahrradverleih ist super!");
    }

}
