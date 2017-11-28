package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Author extends DBModel {
  public String firstName;
  public String lastName;
  public String biography;
  @DBRef
  public List<Category> categories;


  public Author() {}

  public Author(String firstName, String lastName, String biography, List<Category> categories) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.biography = biography;
    this.categories = categories;
  }
}
