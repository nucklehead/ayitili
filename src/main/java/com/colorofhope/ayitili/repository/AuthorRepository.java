package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {}
