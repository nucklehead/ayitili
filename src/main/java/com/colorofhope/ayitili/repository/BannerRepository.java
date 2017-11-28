package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Author;
import com.colorofhope.ayitili.model.Banner;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.IOException;

public interface BannerRepository extends MongoRepository<Banner, String> {}
