package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Author;
import com.colorofhope.ayitili.model.Nav;
import com.colorofhope.ayitili.repository.AuthorRepository;
import com.colorofhope.ayitili.repository.NavRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nav")
public class NavController extends DefaultController<NavRepository, Nav> {
  public NavController(NavRepository navRepository) {
    this.repository = navRepository;
  }
}
