package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Sponsor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SponsorRepository extends MongoRepository<Sponsor, String> {}
