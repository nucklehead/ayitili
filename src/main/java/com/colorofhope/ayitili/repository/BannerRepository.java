package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerRepository extends MongoRepository<Banner, String> {}
