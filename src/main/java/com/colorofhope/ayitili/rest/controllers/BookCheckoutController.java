package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.BookCheckout;
import com.colorofhope.ayitili.repository.BookCheckoutRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookCheckout")
public class BookCheckoutController
    extends DefaultController<BookCheckoutRepository, BookCheckout> {
  public BookCheckoutController(BookCheckoutRepository bookCheckoutRepository) {
    this.repository = bookCheckoutRepository;
  }
}
