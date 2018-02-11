package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Author;
import com.colorofhope.ayitili.model.Page;
import com.colorofhope.ayitili.repository.AuthorRepository;
import com.colorofhope.ayitili.repository.PageRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/page")
public class PageController extends DefaultController<PageRepository, Page> {
  public PageController(PageRepository pageRepository) {
    this.repository = pageRepository;
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{name}/component")
  public Page insertComponent(@PathVariable String name, Integer rowIdex, Integer columnIndex, String component){
    Page page = repository.findByName(name);
    if(page.bodyRows.size() < rowIdex){
      page.getBodyRows().add(Arrays.asList(component));
    }
    else if(page.bodyRows.get(rowIdex).size() < columnIndex) {
      page.bodyRows.get(rowIdex).add(component);
    }
    else {
      page.bodyRows.get(rowIdex).set(columnIndex, component);
    }
    return repository.save(page);
  }
}
