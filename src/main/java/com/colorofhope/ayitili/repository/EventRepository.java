package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {}
