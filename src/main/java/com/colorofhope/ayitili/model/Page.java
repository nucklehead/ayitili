package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IMAGE_DIV;
import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_TEXT_DIV;

public class Page extends DBModel {
  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Non")
  @Indexed
  public String name;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Aktiv")
  public Boolean active;

  @BootstrapHtmlDisplay(HTML_IMAGE_DIV)
  @BootstrapLabel("Kò")
  @DBRef
  public List<List<String>> bodyRows = Arrays.asList();

  public Page() {}

  public Page(String name, Boolean active, List<List<String>> bodyRows) {
    this.name = name;
    this.active = active;
    this.bodyRows = bodyRows;
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
}
