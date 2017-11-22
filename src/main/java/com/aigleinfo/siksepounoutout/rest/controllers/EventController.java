package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Event;
import com.aigleinfo.siksepounoutout.repository.EventRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@Api
public class EventController {
  @Autowired EventRepository eventRepository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Event createEvent(@RequestBody Event event) {
    return eventRepository.save(event);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{eventID}")
  public Event getEventById(@PathVariable String eventID) {
    return eventRepository.findOne(eventID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{eventID}")
  public Event updateEvent(@PathVariable String eventID, @RequestBody Event event) {
    event.id = eventID;
    return eventRepository.save(event);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{eventID}")
  public void deleteEventById(@PathVariable String eventID) {
    eventRepository.delete(eventID);
  }
}
