package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Option {
  @Id public String id;

  public String name;
  public String description;

  public Option() {}

  public Option(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
