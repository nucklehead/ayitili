package com.colorofhope.ayitili.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_TEXT_DIV;

public class BookAssesment extends MemberAction {

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Sijè")
  public String assessment;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Rapò")
  public TextArea content;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Liv")
  @DBRef public Book book;


  public BookAssesment() {
    this.type = MemberActionType.BOOK_ASSESSMENT;
  }

  public BookAssesment(Account member, TextArea content, Book book, String assessment) {
    super(member, MemberActionType.BOOK_ASSESSMENT);
    this.content = content;
    this.book = book;
    this.assessment = assessment;
  }

  public String getContent() {
    return content.value;
  }

  public void setContent(String content) {
    this.content = new TextArea(content);
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public String getAssessment() {
    return assessment;
  }

  public void setAssessment(String assessment) {
    this.assessment = assessment;
  }

}
