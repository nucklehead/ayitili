package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.*;

public class Nav extends DBModel {
  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Tèx")
  public String text;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Lyen")
  public String link;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Kalite")
  public NavType type;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Aksè")
  public List<AccountType> accessTypes = new ArrayList<>();


  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Lis deroulan")
  public List<Nav> dropdown = new ArrayList<>();

  @Transient
  public List<String> navIds = new ArrayList<>();

  public Nav() {}

  public Nav(String text, String link, NavType type, List<AccountType> accessTypes, List<Nav> dropdown) {
    this.text = text;
    this.link = link;
    this.type = type;
    this.accessTypes = accessTypes;
    this.dropdown = dropdown;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public NavType getType() {
    return type;
  }

  public void setType(NavType type) {
    this.type = type;
  }

  public List<AccountType> getAccessTypes() {
    return accessTypes;
  }

  public void setAccessTypes(List<AccountType> accessTypes) {
    this.accessTypes = accessTypes;
  }

  public List<Nav> getDropdown() {
    return dropdown;
  }

  public void setDropdown(List<Nav> dropdown) {
    this.dropdown = dropdown;
  }

  public List<String> getNavIds() {
    return null;
  }

  public void setNavIds(List<String> navIds) {
    this.navIds = navIds;
  }

  @Override
  public String toString() {
    return text;
  }
}
