package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Tag;
import com.colorofhope.ayitili.repository.TagRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
public class TagController extends DefaultController<TagRepository, Tag> {
  public TagController(TagRepository tagRepository) {
    this.repository = tagRepository;
  }
}
