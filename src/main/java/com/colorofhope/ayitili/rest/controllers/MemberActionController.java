package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Category;
import com.colorofhope.ayitili.model.MemberAction;
import com.colorofhope.ayitili.repository.CategoryRepository;
import com.colorofhope.ayitili.repository.MemberActionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memberAction")
public class MemberActionController extends DefaultController<MemberActionRepository, MemberAction> {
  public MemberActionController(MemberActionRepository memberActionRepository) {
    this.repository = memberActionRepository;
  }
}
