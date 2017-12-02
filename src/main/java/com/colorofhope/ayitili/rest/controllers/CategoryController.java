package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Category;
import com.colorofhope.ayitili.repository.CategoryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends DefaultController<CategoryRepository, Category> {
  public CategoryController(CategoryRepository categoryRepository) {
    this.repository = categoryRepository;
  }
}
