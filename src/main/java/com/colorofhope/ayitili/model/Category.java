package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Category extends DBModel {
  public String name;
  public String description;

  public Category() {}

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
