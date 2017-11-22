package com.aigleinfo.siksepounoutout.model;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Event {
  @Id public String id;

  public String title;
  public Date date;
  public String description;
  public String speaker;
  public String location;
  public String optionID;

  public Event() {}

  public Event(String title, Date date, String description, String speaker, String location) {
    this.title = title;
    this.date = date;
    this.description = description;
    this.speaker = speaker;
    this.location = location;
  }
}
