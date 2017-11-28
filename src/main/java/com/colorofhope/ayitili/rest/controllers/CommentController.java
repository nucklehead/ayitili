package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.Comment;
import com.colorofhope.ayitili.repository.AccountRepository;
import com.colorofhope.ayitili.repository.CommentRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController extends DefaultController<CommentRepository, Comment> {
  public CommentController(CommentRepository commentRepository) {
    this.repository = commentRepository;
  }
}
