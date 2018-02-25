package com.colorofhope.ayitili.web;

import com.colorofhope.ayitili.model.AccountType;
import com.colorofhope.ayitili.model.Nav;
import com.colorofhope.ayitili.repository.NavRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ModelAttributeControllerAdvice {
  @Autowired NavRepository navRepository;

  @ModelAttribute("iniere")
  public String test(Authentication user) {
    //    Map<String, String> links1 = navlinks(user);
    //    links1.putAll(navButtons(null));
    //    links1.putAll(navForms(user));
    //    List<Nav> myNavs = links1.entrySet().stream().map(nav -> new Nav(nav.getKey(),
    // nav.getValue(), NavType.INTERNAL, Arrays.asList(AccountType.GUEST_MEMBER),
    // Arrays.asList())).collect(Collectors.toList());
    //    navRepository.save(myNavs);
    return "tesy";
  }

  @ModelAttribute("accessibleNavs")
  public List accessibleNavs(Authentication user) {
    List<Nav> allNavs = navRepository.findAll();
    allNavs.sort(Comparator.comparing(Nav::getType).thenComparing(Nav::getText));
    List<AccountType> userAccess = new ArrayList<>();
    if (user != null) {
      userAccess.addAll(
          user.getAuthorities()
              .stream()
              .map(authority -> AccountType.valueOf(authority.getAuthority()))
              .collect(Collectors.toList()));
    } else {
      userAccess.add(AccountType.GUEST_MEMBER);
    }
    List<Nav> accessibleNavs =
        allNavs
            .stream()
            .filter(nav -> nav.accessTypes.containsAll(userAccess))
            .collect(Collectors.toList());
    return accessibleNavs;
  }

  @ModelAttribute("navLinks")
  public Map navlinks(Authentication user) {
    Map<String, String> navLinks = new LinkedHashMap<>();
    navLinks.put("Akèy", "/");
    navLinks.put("Liv", "/showBooks");
    if (user != null) {
      if (user.getAuthorities().contains(new SimpleGrantedAuthority(AccountType.ADMIN.name()))) {
        navLinks.put("Ajoute manm", "/ajouteManm");
        navLinks.put("Montre Banyè yo", "/showBanners");
      }
      navLinks.put("Kont:" + user.getName(), "#");
    }
    return navLinks;
  }

  @ModelAttribute("navButtons")
  public Map navButtons(Authentication user) {
    Map<String, String> navButtons = new LinkedHashMap<>();
    if (user == null) {
      navButtons.put("Antre", "#antre");
    }
    return navButtons;
  }

  @ModelAttribute("navForms")
  public Map navForms(Authentication user) {
    Map<String, String> navForms = new LinkedHashMap<>();
    if (user != null) {
      navForms.put("Soti", "/logout");
    }
    return navForms;
  }

  @ModelAttribute("author")
  public String author() {
    return "Jean Evans Pierre";
  }

  @ModelAttribute("description")
  public String description() {
    return "Ayiti li. Kisa liye?";
  }

  @ModelAttribute("siteName")
  public String siteName() {
    return "Ayiti li";
  }

  @ModelAttribute("siteSubtitle")
  public String siteSubtitle() {
    return "Nenpòt sa'w bezwen w'ap jwenn li nan yon liv";
  }

  @ModelAttribute("addButtonText")
  public String addButtonText() {
    return "Ajoute";
  }

  @ModelAttribute("submitButtonText")
  public String submitButtonText() {
    return "Fini";
  }

  @ModelAttribute("usernameLable")
  public String usernameLable() {
    return "Non itilizatè";
  }

  @ModelAttribute("passwordLabel")
  public String passwordLabel() {
    return "Mo pass";
  }

  @ModelAttribute("rememberText")
  public String rememberText() {
    return "Songe òdinatè sa a";
  }

  @ModelAttribute("loginButtonText")
  public String loginButtonText() {
    return "Antre";
  }

  @ModelAttribute("changeButtonText")
  public String changeButtonText() {
    return "Change";
  }

  @ModelAttribute("deleteButtonText")
  public String deleteButtonText() {
    return "Efase";
  }

  @ModelAttribute("signupButtonText")
  public String signupButtonText() {
    return "Anrejistre";
  }
}
