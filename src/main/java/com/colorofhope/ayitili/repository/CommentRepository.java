package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {}
