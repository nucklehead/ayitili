package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.repository.AccountRepository;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController extends DefaultController<AccountRepository, Account> {
  @Autowired PasswordEncoder passwordEncoder;

  public AccountController(AccountRepository accountRepository) {
    this.repository = accountRepository;
  }

  @Override
  public Object create(Account model, String returnPath) throws IOException {
    model.password = passwordEncoder.encode(model.password);
    return super.create(model, returnPath);
  }

  @Override
  public Object update(String id, Account model, String returnPath) throws IOException {
    model.password = passwordEncoder.encode(model.password);
    return super.update(id, model, returnPath);
  }
}
