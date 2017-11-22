package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Option;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionRepository extends MongoRepository<Option, String> {}
