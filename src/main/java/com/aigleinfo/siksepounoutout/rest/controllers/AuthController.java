package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Account;
import com.aigleinfo.siksepounoutout.model.Token;
import com.aigleinfo.siksepounoutout.repository.AccountRepository;
import io.swagger.annotations.Api;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Api
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
