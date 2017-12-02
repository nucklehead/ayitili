package com.colorofhope.ayitili.model;

import java.time.Duration;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class BookCheckout extends MemberAction {

  public String content;
  @DBRef public Book book;
  public Duration duration;

  public BookCheckout() {}

  public BookCheckout(Account member, String content, Book book) {
    super(member, MemberActionType.BOOK_CHECKOUT);
    this.content = content;
    this.book = book;
  }
}
