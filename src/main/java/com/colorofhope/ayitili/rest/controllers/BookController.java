package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Banner;
import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.Option;
import com.colorofhope.ayitili.repository.BookRepository;
import com.colorofhope.ayitili.repository.DBImageRepository;
import com.colorofhope.ayitili.repository.OptionRepository;
import io.swagger.annotations.Api;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/book")
public class BookController extends DefaultController<BookRepository, Book> {
  @Autowired
  GridFsTemplate gridFsTemplate;

  @Autowired
  DBImageRepository dbImageRepository;

  public BookController(BookRepository bookRepository) {
    this.repository = bookRepository;
  }

  @Override
  public Book create(Book model) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.create(model);
  }

  @Override
  public Book update(@PathVariable String id, Book model) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.update(id, model);
  }

  @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
  void handleBadRequests(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler({IOException.class})
  void handleIOExceptions(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), "Unable to read image file.");
  }
}
