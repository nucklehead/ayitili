package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {}
