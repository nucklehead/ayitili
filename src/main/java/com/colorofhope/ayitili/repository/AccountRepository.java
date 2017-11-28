package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
  public Account findByUsername(String username);
}
