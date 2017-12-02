package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Sponsor;
import com.colorofhope.ayitili.repository.SponsorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sponsor")
public class SponsorController extends DefaultController<SponsorRepository, Sponsor> {
  public SponsorController(SponsorRepository sponsorRepository) {
    this.repository = sponsorRepository;
  }
}
