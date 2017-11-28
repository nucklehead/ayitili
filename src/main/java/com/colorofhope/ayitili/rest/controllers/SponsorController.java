package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Book;
import com.colorofhope.ayitili.model.Sponsor;
import com.colorofhope.ayitili.repository.BookRepository;
import com.colorofhope.ayitili.repository.SponsorRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sponsor")
public class SponsorController extends DefaultController<SponsorRepository, Sponsor> {
  public SponsorController(SponsorRepository sponsorRepository) {
    this.repository = sponsorRepository;
  }
}
