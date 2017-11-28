package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {}
