package com.aigleinfo.siksepounoutout.repository;

import com.aigleinfo.siksepounoutout.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
  public Account findByUsername(String username);
}
