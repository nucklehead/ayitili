package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Event;
import com.colorofhope.ayitili.model.Option;
import com.colorofhope.ayitili.repository.EventRepository;
import com.colorofhope.ayitili.repository.OptionRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/option")
public class OptionController extends DefaultController<OptionRepository, Option> {
  public OptionController(OptionRepository optionRepository) {
    this.repository = optionRepository;
  }
}
