package com.colorofhope.ayitili.model;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IMAGE_DIV;
import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_TEXT_DIV;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

public class Page extends DBModel {
  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Non")
  @Indexed
  public String name;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Non lyen")
  @Indexed
  public String formatedName;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Aktiv")
  public Boolean active = false;

  @BootstrapHtmlDisplay(HTML_IMAGE_DIV)
  @BootstrapLabel("Kò")
  public List<List<String>> bodyRows = Arrays.asList();

  @BootstrapHtmlDisplay(HTML_IMAGE_DIV)
  @BootstrapLabel("Viyèt")
  @DBRef
  public DBImage thumbnail;

  @BootstrapLabel("Viyèt")
  @Transient
  public MultipartFile formThumbnail;

  public Page() {}

  public Page(
      String name,
      String formatedName,
      Boolean active,
      List<List<String>> bodyRows,
      MultipartFile formThumbnail,
      DBImage thumbnail) {
    this.name = name;
    this.formatedName = formatedName;
    this.active = active;
    this.bodyRows = bodyRows;
    this.formThumbnail = formThumbnail;
    this.thumbnail = thumbnail;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<List<String>> getBodyRows() {
    return bodyRows;
  }

  public void setBodyRows(List<List<String>> bodyRows) {
    this.bodyRows = bodyRows;
  }

  public String getFormatedName() {
    return formatedName;
  }

  public void setFormatedName(String formatedName) {
    this.formatedName = formatedName;
  }

  public MultipartFile getFormThumbnail() {
    return null;
  }

  public void setFormThumbnail(MultipartFile formThumbnail) {
    this.formThumbnail = formThumbnail;
  }

  public DBImage getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(DBImage thumbnail) {
    this.thumbnail = thumbnail;
  }
}
