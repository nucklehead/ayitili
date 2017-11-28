package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Id;

public class Account extends DBModel {
  @BootstrapLabel("Non Itilizatè")
  public String username;
  @BootstrapLabel("Mo pass")
  public String password;
  @BootstrapLabel("Kourye-e")
  public String email;
  @BootstrapLabel("Tefòn")
  public String phone;

  @BootstrapLabel("Prenon")
  public String firstName;
  @BootstrapLabel("Non")
  public String lastName;
  @BootstrapLabel("Kalite kont lan")
  public AccounteType type;
  @BootstrapLabel("Pwen")
  public Long points = 0L;

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
