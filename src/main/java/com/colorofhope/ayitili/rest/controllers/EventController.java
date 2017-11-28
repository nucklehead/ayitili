package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Comment;
import com.colorofhope.ayitili.model.Event;
import com.colorofhope.ayitili.repository.CommentRepository;
import com.colorofhope.ayitili.repository.EventRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController extends DefaultController<EventRepository, Event> {
  public EventController(EventRepository eventRepository) {
    this.repository = eventRepository;
  }
}
