package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.Nav;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NavRepository extends MongoRepository<Nav, String> {}
