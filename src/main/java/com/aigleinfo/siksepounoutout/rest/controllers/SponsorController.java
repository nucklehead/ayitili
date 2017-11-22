package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Sponsor;
import com.aigleinfo.siksepounoutout.repository.SponsorRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sponsor")
@Api
public class SponsorController {
  @Autowired SponsorRepository sponsorRepository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Sponsor> getSponsors() {
    return sponsorRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
    return sponsorRepository.save(sponsor);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{sponsorID}")
  public Sponsor getSponsorById(@PathVariable String sponsorID) {
    return sponsorRepository.findOne(sponsorID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{sponsorID}")
  public Sponsor updateSponsor(@PathVariable String sponsorID, @RequestBody Sponsor sponsor) {
    sponsor.id = sponsorID;
    return sponsorRepository.save(sponsor);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{sponsorID}")
  public void deleteSponsorById(@PathVariable String sponsorID) {
    sponsorRepository.delete(sponsorID);
  }
}
