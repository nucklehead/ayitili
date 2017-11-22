package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {}
