package com.colorofhope.ayitili.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class Tag extends DBModel {
  @Indexed public String name;

  public Tag() {}

  public Tag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
