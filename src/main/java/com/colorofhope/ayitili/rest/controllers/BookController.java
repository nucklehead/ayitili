package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.repository.BookRepository;
import com.colorofhope.ayitili.repository.DBImageRepository;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController extends DefaultController<BookRepository, Book> {
  @Autowired GridFsTemplate gridFsTemplate;

  @Autowired DBImageRepository dbImageRepository;

  public BookController(BookRepository bookRepository) {
    this.repository = bookRepository;
  }

  @Override
  public Object create(Book model, String returnPath) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.create(model, returnPath);
  }

  @Override
  public Object update(@PathVariable String id, Book model, String returnPath) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.update(id, model, returnPath);
  }

  @Override
  public void delete(@PathVariable String id) {
    dbImageRepository.delete(repository.findOne(id).image.id);
    super.delete(id);
  }

  @PreAuthorize("permitAll")
  @RequestMapping(method = RequestMethod.GET, path = "/list")
  public List<Book> getPage(Pageable pageable) {
    return repository.findAll(pageable).getContent();
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
