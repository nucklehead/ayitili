package com.colorofhope.ayitili.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class BookAssesment extends MemberAction {

  public String content;
  @DBRef public Book book;
  public String assessment;

  public BookAssesment() {}

  public BookAssesment(Account member, String content, Book book, String assessment) {
    super(member, MemberActionType.BOOK_ASSESSMENT);
    this.content = content;
    this.book = book;
    this.assessment = assessment;
  }
}
