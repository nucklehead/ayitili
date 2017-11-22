package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Account;
import com.aigleinfo.siksepounoutout.repository.AccountRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@Api
public class AccountController {
  @Autowired AccountRepository accountRepository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Account> getAccounts() {
    return accountRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Account createAccount(@RequestBody Account account) {
    return accountRepository.save(account);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{accountID}")
  public Account getAccountById(@PathVariable String accountID) {
    return accountRepository.findOne(accountID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{accountID}")
  public Account updateAccount(@PathVariable String accountID, @RequestBody Account account) {
    account.id = accountID;
    return accountRepository.save(account);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{accountID}")
  public void deleteAccountById(@PathVariable String accountID) {
    accountRepository.delete(accountID);
  }
}
