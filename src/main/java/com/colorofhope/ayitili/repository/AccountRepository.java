package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.AccounteType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
  Account findByUsername(String username);

  Account findByType(AccounteType type);
}
