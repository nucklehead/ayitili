package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Account {
  @Id public String id;

  public String username;
  public String password;
  public String email;
  public String phone;

  public String firstName;
  public String lastName;
  
  public AccounteType type;
  
  public Long points;

  public Account() {}

  public Account(
      String username,
      String password,
      String email,
      String phone,
      String firstName,
      String lastName,
      AccounteType type,
      Long points) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.firstName = firstName;
    this.lastName = lastName;
    this.type = type;
    this.points = points;
  }
}
