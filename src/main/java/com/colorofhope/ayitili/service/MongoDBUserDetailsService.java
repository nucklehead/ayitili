package com.colorofhope.ayitili.service;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.AccountType;
import com.colorofhope.ayitili.repository.AccountRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MongoDBUserDetailsService implements UserDetailsService {

  AccountRepository accountRepository;
  String appName;
  String adminUsername;
  String adminPassword;
  PasswordEncoder passwordEncoder;

  @Autowired
  public MongoDBUserDetailsService(
      AccountRepository accountRepository,
      PasswordEncoder passwordEncoder,
      @Value("${spring.application.name}") String appName,
      @Value("${security.user.name}") String adminUsername,
      @Value("${security.user.password}") String adminPassword) {
    this.accountRepository = accountRepository;
    this.passwordEncoder = passwordEncoder;
    this.appName = appName;
    this.adminUsername = adminUsername;
    this.adminPassword = adminPassword;
    Account adminAccount = accountRepository.findByType(AccountType.ADMIN);
    if (adminAccount == null) {
      adminAccount = new Account();
    }
    adminAccount.username = adminUsername;
    adminAccount.password = passwordEncoder.encode(adminPassword);
    adminAccount.email = appName + "@test.com";
    adminAccount.phone = "";
    adminAccount.firstName = appName.split("-")[0];
    adminAccount.lastName = appName.split("-")[1];
    adminAccount.type = AccountType.ADMIN;
    adminAccount.points = 0L;
    accountRepository.save(adminAccount);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails loadedUser;
    Account account = accountRepository.findByUsername(username);
    loadedUser =
        new User(
            account.username,
            account.password,
            Arrays.asList(new SimpleGrantedAuthority(account.type.name())));
    return loadedUser;
  }
}
