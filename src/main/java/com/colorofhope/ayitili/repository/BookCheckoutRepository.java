package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.BookCheckout;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookCheckoutRepository extends MongoRepository<BookCheckout, String> {}
