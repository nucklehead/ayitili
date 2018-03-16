package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.DBModel;
import com.colorofhope.ayitili.repository.DBModelESRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RequestMapping("/search")
@RestController
public class DBModelESController {
  @Autowired
  DBModelESRepository repository;

  @RequestMapping(method = RequestMethod.GET)
  public List<DBModel> searchText(@RequestParam("text") String text, Pageable pageable) {
    return repository.findMatched(text, pageable).getContent();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/autocomplete")
  public List<DBModel> searchAutocomplete(@RequestParam("text") String text, Pageable pageable) {
    return repository.predictMatch(text, pageable).getContent();
  }

}
