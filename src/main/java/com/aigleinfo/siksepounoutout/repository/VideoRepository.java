package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {}
