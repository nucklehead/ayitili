package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Id;

public class Comment extends DBModel {
  public String user;
  public String content;

  public Comment() {}

  public Comment(String user, String content) {
    this.user = user;
    this.content = content;
  }
}
