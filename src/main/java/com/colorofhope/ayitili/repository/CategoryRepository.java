package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.BookCheckout;
import com.colorofhope.ayitili.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}
