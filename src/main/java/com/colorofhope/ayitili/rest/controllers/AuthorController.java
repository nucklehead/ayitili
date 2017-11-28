package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.Author;
import com.colorofhope.ayitili.repository.AccountRepository;
import com.colorofhope.ayitili.repository.AuthorRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorController extends DefaultController<AuthorRepository, Author> {
  public AuthorController(AuthorRepository authorRepository) {
    this.repository = authorRepository;
  }
}
