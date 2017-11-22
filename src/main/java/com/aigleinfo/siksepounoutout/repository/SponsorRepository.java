package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Sponsor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SponsorRepository extends MongoRepository<Sponsor, String> {}
