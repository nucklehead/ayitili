package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Comment;
import com.colorofhope.ayitili.repository.CommentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController extends DefaultController<CommentRepository, Comment> {
  public CommentController(CommentRepository commentRepository) {
    this.repository = commentRepository;
  }
}
