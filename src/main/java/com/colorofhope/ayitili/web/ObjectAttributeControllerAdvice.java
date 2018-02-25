package com.colorofhope.ayitili.web;

import com.colorofhope.ayitili.model.*;
import com.colorofhope.ayitili.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ObjectAttributeControllerAdvice {

  @Autowired BannerRepository bannerRepository;

  @Autowired BookRepository bookRepository;

  @Autowired NavRepository navRepository;

  @Autowired AuthorRepository authorRepository;

  @Autowired AccountRepository accountRepository;

  @ModelAttribute("banner")
  public Banner banner() {
    return new Banner();
  }

  @ModelAttribute("banners")
  public List banners() {
    return bannerRepository.findAll();
  }

  @ModelAttribute("book")
  public Book book() {
    return new Book();
  }

  @ModelAttribute("books")
  public List books() {
    return bookRepository.findAll();
  }

  @ModelAttribute("nav")
  public Nav nav() {
    return new Nav();
  }

  @ModelAttribute("navs")
  public List navs() {
    return navRepository.findAll();
  }

  @ModelAttribute("author")
  public Author author() {
    return new Author();
  }

  @ModelAttribute("authors")
  public List authors() {
    return authorRepository.findAll();
  }

  @ModelAttribute("account")
  public Account account() {
    return new Account();
  }

  @ModelAttribute("accounts")
  public List accounts() {
    return accountRepository.findAll();
  }
}
