package com.colorofhope.ayitili.model;

public class Option extends DBModel {
  public String name;
  public String description;

  public Option() {}

  public Option(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
