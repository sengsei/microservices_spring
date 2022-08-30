package de.prototype.comment.controller;

import de.prototype.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;
}
