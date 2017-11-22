package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Sponsor {
  @Id public String id;

  public String name;
  public String description;

  public Sponsor() {}

  public Sponsor(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
