package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.BookAssesment;
import com.colorofhope.ayitili.repository.BookAssesmentRepository;
import com.colorofhope.ayitili.repository.BookRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookAssesment")
public class BookAssesmentController extends DefaultController<BookAssesmentRepository, BookAssesment> {
  public BookAssesmentController(BookAssesmentRepository bookAssesmentRepository) {
    this.repository = bookAssesmentRepository;
  }
}
