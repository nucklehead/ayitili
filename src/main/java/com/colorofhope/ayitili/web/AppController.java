package com.colorofhope.ayitili.web;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.Banner;
import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.Page;
import com.colorofhope.ayitili.repository.BannerRepository;
import com.colorofhope.ayitili.repository.BookRepository;
import com.colorofhope.ayitili.repository.PageRepository;
import com.colorofhope.ayitili.rest.controllers.AccountController;
import com.colorofhope.ayitili.rest.controllers.OptionController;
import com.colorofhope.ayitili.rest.controllers.VideoController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api
public class AppController {

  @Autowired OptionController optionController;

  @Autowired VideoController videoController;

  @Autowired BannerRepository bannerRepository;

  @Autowired BookRepository bookRepository;

  @Autowired PageRepository pageRepository;

  @Autowired AccountController accountController;

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public String index(Model model) {
    model.addAttribute("title", "Ayiti li - Akèy");
    model.addAttribute("optionTitle", "Opsyon ki genyen yo:");

    model.addAttribute("options", optionController.getAll());
    model.addAttribute("fbVideos", videoController.getFacebookVideos());
    model.addAttribute("pageVideoPath", "Aigleinfo/videos");
    model.addAttribute("banners", bannerRepository.findAll());
    //    model.addAttribute("books", bookRepository.findAll());

    Integer numBooksPerCarousel = 6;
    model.addAttribute("books", Lists.partition(bookRepository.findAll(), numBooksPerCarousel));
    model.addAttribute("numBooksPerCarousel", numBooksPerCarousel);

    // TODO
    // https://stackoverflow.com/questions/38176994/thymeleaf-with-springboot-how-to-loop-model-and-delete

    // TODO: will be useful for user actions
    // https://www.codeply.com/go/JVP2nxfv0p/bootstrap-4-form-examples

    return "home";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/login")
  public String login(Model model) {
    model.addAttribute("title", "Ayiti li - login");
    model.addAttribute("loginTittle", "Monte sou sit la");
    return "login";
  }

  @RequestMapping(method = RequestMethod.POST, path = "/signup")
  public String signup(Account account, String returnPath) throws IOException {
    accountController.create(account, "/");

    return "redirect:" + returnPath;
  }

  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('LIBRARIAN')")
  @RequestMapping(method = RequestMethod.GET, path = "/ajouteManm")
  public String addMember(Model model) {
    model.addAttribute("title", "Ayiti li - Ajoute");
    model.addAttribute("account", new Account());
    return "addAccount";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, path = "/showBanners")
  public String showBanners(Model model) {
    model.addAttribute("title", "Ayiti li - Banyè");
    model.addAttribute("banners", bannerRepository.findAll());
    model.addAttribute("banner", new Banner());
    return "bannerList";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/showBooks")
  public String showBooks(Model model) {
    model.addAttribute("title", "Ayiti li - Liv");
    model.addAttribute("books", bookRepository.findAll());
    model.addAttribute("book", new Book());
    return "bookList";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, path = "**/edit")
  public String editCurrentPage(Model model, HttpServletRequest request) {
    String requestURL = request.getRequestURI();
    String pageUrl = requestURL.replaceAll("/edit", "");
    model.addAttribute("editMode", true);
    return "forward:" + pageUrl;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/page/{name}")
  public String renderDbPage(Model model, String name) {
    Page page = pageRepository.findByFormatedName(name);
    if (page.active) {
      model.addAttribute("currentPage", page);
      model.addAttribute("title", page.name);
      return "pageView";
    }
    //    TODO you know
    return "error/404";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, path = "/page/{name}/edit")
  public String editDBPage(Model model, @PathVariable String name) {
    Page page = pageRepository.findByFormatedName(name);
    if (page != null) {
      model.addAttribute("currentPage", page);
      model.addAttribute("title", page.name + " - change");
      return "pageEditView";
    }
    return "error/404";
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @RequestMapping(method = RequestMethod.GET, path = "/page/create")
  public String renderDbPage(Model model) {
    model.addAttribute("title", "Kreye paj");
    return "pageEditView";
  }
}
