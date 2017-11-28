package com.colorofhope.ayitili.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.colorofhope.ayitili.model.BootstrapHtmlDisplay.HTML_IMAGE_DIV;

public class Book extends DBModel {
  public String title;
  @DBRef
  public List<Author> authors;
  public String summary;
  public Integer pages;
  @DBRef
  public List<Category> categories;

    @DBRef
    public DBImage image;

    @Transient
    public MultipartFile formImage;

    public Book() {}

    public Book(String title, List<Author> authors, String summary, Integer pages, List<Category> categories, DBImage image, MultipartFile formImage) {
        this.title = title;
        this.authors = authors;
        this.summary = summary;
        this.pages = pages;
        this.categories = categories;
        this.image = image;
        this.formImage = formImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public DBImage getImage() {
        return image;
    }

    public void setImage(DBImage image) {
        this.image = image;
    }

    public MultipartFile getFormImage() {
        return formImage;
    }

    public void setFormImage(MultipartFile formImage) {
        this.formImage = formImage;
    }
}
