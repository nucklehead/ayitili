package com.colorofhope.ayitili.web;

import com.colorofhope.ayitili.model.Account;
import com.colorofhope.ayitili.model.Banner;
import com.colorofhope.ayitili.repository.BannerRepository;
import com.colorofhope.ayitili.repository.BookRepository;
import com.colorofhope.ayitili.rest.controllers.OptionController;
import com.colorofhope.ayitili.rest.controllers.VideoController;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api
public class AppController {

  public static Integer BOOTSTRAP_COLUMNS = 12;

  @Autowired
  OptionController optionController;

  @Autowired
  VideoController videoController;

  @Autowired
  BannerRepository bannerRepository;

  @Autowired
  BookRepository bookRepository;


  @ModelAttribute("navLinks")
  public Map navlinks(){
    Map<String, String> navLinks = new HashMap<>();
    navLinks.put("Akèy", "/");
    navLinks.put("Liv", "#liv");
    navLinks.put("Ajoute manm", "/ajouteManm");
    navLinks.put("Antre", "#antre");
    return navLinks;
  }

//  TODO: May need to move all to Properties class using properties file
  @ModelAttribute("author")
  public String author(){
    return "Jean Evans Pierre";
  }

  @ModelAttribute("bootstrapColumns")
  public Integer bootstrapColumns(){
    return BOOTSTRAP_COLUMNS;
  }

  @ModelAttribute("description")
  public String description(){
    return "Ayiti li. Kisa liye?";
  }

  @ModelAttribute("siteName")
  public String siteName(){
    return "Ayiti li";
  }

  @ModelAttribute("siteSubtitle")
  public String siteSubtitle(){
    return "Nenpòt sa'w bezwen w'ap jwenn li nan yon liv";
  }
  @ModelAttribute("addButtonText")
  public String addButtonText(){
    return "Ajoute";
  }

  @ModelAttribute("submitButtonText")
  public String submitButtonText(){
    return "Fini";
  }

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


    // Add logo filename or link
    // Add book lists
    // Add banner pictures or objects
    // Login
//    https://stackoverflow.com/questions/38176994/thymeleaf-with-springboot-how-to-loop-model-and-delete

    return "home";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/ajouteManm")
  public String addMember(Model model) {
    model.addAttribute("title", "Ayiti li - Ajoute");
    model.addAttribute("account", new Account());
    return "addAccount";
  }

  @RequestMapping(method = RequestMethod.GET, path = "/showBanners")
  public String showBanners(Model model) {
    model.addAttribute("title", "Ayiti li - Banyè");
    model.addAttribute("banners", bannerRepository.findAll());
    model.addAttribute("banner", new Banner());
    return "bannerList";
  }

}
