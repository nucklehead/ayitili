package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Nav;
import com.colorofhope.ayitili.repository.NavRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/nav")
public class NavController extends DefaultController<NavRepository, Nav> {
  public NavController(NavRepository navRepository) {
    this.repository = navRepository;
  }

  @Override
  public Object create(Nav model, String returnPath) throws IOException {
    Iterable<Nav> subNavs =  repository.findAll(model.navIds);
    subNavs.forEach(model.dropdown::add);
    return super.create(model, returnPath);
  }

  @Override
  public Object update(@PathVariable String id, Nav model, String returnPath) throws IOException {
    Iterable<Nav> subNavs =  repository.findAll(model.navIds);
    subNavs.forEach(model.dropdown::add);
    return super.update(id, model, returnPath);
  }
}
