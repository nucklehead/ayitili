package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Comment;
import com.aigleinfo.siksepounoutout.repository.CommentRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Api
public class CommentController {
  @Autowired CommentRepository commentRepository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Comment> getComments() {
    return commentRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Comment createComment(@RequestBody Comment comment) {
    return commentRepository.save(comment);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{commentID}")
  public Comment getCommentById(@PathVariable String commentID) {
    return commentRepository.findOne(commentID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{commentID}")
  public Comment updateComment(@PathVariable String commentID, @RequestBody Comment comment) {
    comment.id = commentID;
    return commentRepository.save(comment);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{commentID}")
  public void deleteCommentById(@PathVariable String commentID) {
    commentRepository.delete(commentID);
  }
}
