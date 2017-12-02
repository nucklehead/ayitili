package com.colorofhope.ayitili.model;

public class Sponsor extends DBModel {
  public String name;
  public String description;

  public Sponsor() {}

  public Sponsor(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
