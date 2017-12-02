package com.colorofhope.ayitili.model;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IMAGE_DIV;
import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_TEXT_DIV;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

public class Banner extends DBModel {
  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Antèt")
  public String header;

  @BootstrapHtmlDisplay(HTML_TEXT_DIV)
  @BootstrapLabel("Tèks")
  public String text;

  @BootstrapLabel("Imaj")
  @Transient
  public MultipartFile formImage;

  @BootstrapHtmlDisplay(HTML_IMAGE_DIV)
  @BootstrapLabel("Imaj")
  @DBRef
  public DBImage image;

  public Banner() {}

  public Banner(String header, String text, MultipartFile formImage, DBImage image) {
    this.header = header;
    this.text = text;
    this.formImage = formImage;
    this.image = image;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public MultipartFile getFormImage() {
    return null;
  }

  public void setFormImage(MultipartFile formImage) {
    this.formImage = formImage;
  }

  public DBImage getImage() {
    return image;
  }

  public void setImage(DBImage image) {
    this.image = image;
  }
}
