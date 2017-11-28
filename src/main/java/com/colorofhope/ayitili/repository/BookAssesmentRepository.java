package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.BookAssesment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookAssesmentRepository extends MongoRepository<BookAssesment, String> {}
