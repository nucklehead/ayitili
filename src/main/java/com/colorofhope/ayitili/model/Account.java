package com.colorofhope.ayitili.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class Account extends DBModel {
  @Indexed
  @SearchResponseIgnore
  @BootstrapLabel("Non Itilizatè")
  public String username;

  @SearchResponseIgnore
  @BootstrapLabel("Mo pass")
  public String password;

  @SearchResponseIgnore
  @BootstrapLabel("Kourye-e")
  public String email;

  @SearchResponseIgnore
  @BootstrapLabel("Tefòn")
  public String phone;

  @BootstrapLabel("Prenon")
  public String firstName;

  @BootstrapLabel("Non")
  public String lastName;

  @SearchResponseIgnore
  @BootstrapLabel(value = "Kalite kont lan", showInForm = false)
  public AccountType type = AccountType.MEMBER;

  @SearchResponseIgnore
  @BootstrapLabel(value = "Pwen", showInForm = false)
  public Long points = 0L;

  public Account() {}

  public Account(
      String username,
      String password,
      String email,
      String phone,
      String firstName,
      String lastName,
      AccountType type,
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
  }

  public Long getPoints() {
    return points;
  }

  public void setPoints(Long points) {
    this.points = points;
  }
}
