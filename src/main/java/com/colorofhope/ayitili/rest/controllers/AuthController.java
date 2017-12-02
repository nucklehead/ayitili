package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.Token;
import com.colorofhope.ayitili.repository.AccountRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AuthController {
  @Autowired AccountRepository accountRepository;

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Token login(@RequestBody Account account) {
    Account accountInDB = accountRepository.findByUsername(account.username);
    if (accountInDB.password.equals(account.password)) {
      return new Token(UUID.randomUUID().toString(), "Successfull login.");
    }
    return new Token(null, "Invalid password.");
  }
}
