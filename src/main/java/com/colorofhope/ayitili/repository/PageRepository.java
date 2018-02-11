package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PageRepository extends MongoRepository<Page, String> {
    Page findName(String name);

}
