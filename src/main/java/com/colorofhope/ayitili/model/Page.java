package com.colorofhope.ayitili.model;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IMAGE_DIV;
import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_TEXT_DIV;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

  @SearchResponseIgnore
  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Aktiv")
  public Boolean active = false;

  @SearchResponseIgnore
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

  @BootstrapHtmlDisplay(HTML_IMAGE_DIV)
  @BootstrapLabel("Etikèt")
  @DBRef
  public List<Tag> tags = Arrays.asList();

  @BootstrapLabel("Viyèt")
  @Transient
  public List<String> tagsFormJson = Arrays.asList();

  public Page() {}

  public Page(
      String name,
      String formatedName,
      Boolean active,
      List<List<String>> bodyRows,
      MultipartFile formThumbnail,
      DBImage thumbnail,
      List<Tag> tags,
      List<String> tagsFormJson) {
    this.name = name;
    this.formatedName = formatedName;
    this.active = active;
    this.bodyRows = bodyRows;
    this.formThumbnail = formThumbnail;
    this.thumbnail = thumbnail;
    this.tags = tags;
    this.tagsFormJson = tagsFormJson;
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

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }

  public List<String> getTagsFormJson() {
    return tags.stream()
        .map(
            tag -> {
              try {
                return tag.toJSON();
              } catch (JsonProcessingException e) {
                return "";
              }
            })
        .collect(Collectors.toList());
  }

  public void setTagsFormJson(List<String> tagsFormJson) {
    this.tagsFormJson = tagsFormJson;
  }
}
