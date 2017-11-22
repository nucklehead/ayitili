package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Presenter {
  @Id public String id;

  public String firstName;
  public String lastName;
  public String twitter;
  public String about;
  public String location;
  public String email;
  public String phone;

  public Presenter() {}

  public Presenter(
      String firstName,
      String lastName,
      String twitter,
      String about,
      String location,
      String email,
      String phone) {
    this.twitter = twitter;
    this.about = about;
    this.location = location;
    this.email = email;
    this.phone = phone;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
