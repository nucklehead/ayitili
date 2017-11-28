package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {}
