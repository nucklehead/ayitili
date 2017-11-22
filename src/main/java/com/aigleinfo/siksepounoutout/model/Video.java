package com.aigleinfo.siksepounoutout.model;

import org.springframework.data.annotation.Id;

public class Video {
  @Id public String id;

  public String link;
  public String title;

  public Video() {}

  public Video(String link, String title) {
    this.link = link;
    this.title = title;
  }
}
