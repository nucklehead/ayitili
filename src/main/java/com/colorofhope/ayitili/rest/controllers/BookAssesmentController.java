package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.BookAssesment;
import com.colorofhope.ayitili.repository.BookAssesmentRepository;
import com.colorofhope.ayitili.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/bookAssesment")
public class BookAssesmentController
    extends DefaultController<BookAssesmentRepository, BookAssesment> {

  @Autowired
  BookRepository bookRepository;

  public BookAssesmentController(BookAssesmentRepository bookAssesmentRepository) {
    this.repository = bookAssesmentRepository;
  }

//  @Override
//  public Object create(BookAssesment model, String returnPath) throws IOException {
//    Iterable<Book> subNavs = bookRepository.findAll(model.bookIds);
//    subNavs.forEach(model.books::add);
//    return super.create(model, returnPath);
//  }
//
//  @Override
//  public Object update(@PathVariable String id, BookAssesment model, String returnPath) throws IOException {
//    Iterable<Book> subNavs = bookRepository.findAll(model.bookIds);
//    subNavs.forEach(model.books::add);
//    return super.update(id, model, returnPath);
//  }
}
