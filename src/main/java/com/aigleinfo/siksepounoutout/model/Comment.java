package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Comment {
  @Id public String id;

  public String user;
  public String content;

  public Comment() {}

  public Comment(String user, String content) {
    this.user = user;
    this.content = content;
  }
}
