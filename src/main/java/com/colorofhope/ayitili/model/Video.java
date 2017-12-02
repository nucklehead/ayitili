package com.colorofhope.ayitili.model;

public class Video extends DBModel {
  public String link;
  public String title;

  public Video() {}

  public Video(String link, String title) {
    this.link = link;
    this.title = title;
  }
}
