package com.colorofhope.ayitili.web;

import com.colorofhope.ayitili.model.AccountType;
import com.colorofhope.ayitili.model.Nav;
import com.colorofhope.ayitili.repository.NavRepository;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ModelAttributeControllerAdvice {
  @Autowired NavRepository navRepository;

  @ModelAttribute("accessibleNavs")
  public List<Nav> accessibleNavs(Authentication user) {
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
    accessibleNavs.removeAll(highPrivilegdeNavs(user));
    return accessibleNavs;
  }

  @ModelAttribute("highPrivilegdeNavs")
  public List<Nav> highPrivilegdeNavs(Authentication user) {
    if(!authorized(user)){
      return Arrays.asList();
    }
    List<AccountType> highPrivilegdes = Arrays.asList(AccountType.LIBRARIAN, AccountType.ADMIN);
    List<Nav> allNavs = navRepository
            .findAll()
            .stream()
            .filter(nav -> highPrivilegdes.containsAll(nav.accessTypes))
            .collect(Collectors.toList());
    allNavs.sort(Comparator.comparing(Nav::getType).thenComparing(Nav::getText));
    return allNavs;
  }

  @ModelAttribute("authorized")
  public Boolean authorized(Authentication user) {
    return user != null
        && Arrays.asList(AccountType.ADMIN, AccountType.LIBRARIAN)
            .containsAll(
                user.getAuthorities()
                    .stream()
                    .map(authority -> AccountType.valueOf(authority.getAuthority()))
                    .collect(Collectors.toList()));
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

  @ModelAttribute("goToEditText")
  public String goToEditText() {
    return "Change";
  }

  @ModelAttribute("stopEditingText")
  public String stopEditingText() {
    return "Sispann";
  }

  @ModelAttribute("pageVideoPath")
  public String pageVideoPath() {
    return "AyitiLi/videos";
  }
}
