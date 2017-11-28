package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Option;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionRepository extends MongoRepository<Option, String> {}
