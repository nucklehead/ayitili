package com.aigleinfo.siksepounoutout.rest.controllers;

import com.aigleinfo.siksepounoutout.model.Option;
import com.aigleinfo.siksepounoutout.repository.OptionRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/option")
@Api
public class OptionController {
  @Autowired OptionRepository optionRepository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<Option> getOptions() {
    return optionRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Option createOption(@RequestBody Option option) {
    return optionRepository.save(option);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{optionID}")
  public Option getOptionById(@PathVariable String optionID) {
    return optionRepository.findOne(optionID);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{optionID}")
  public Option updateOption(@PathVariable String optionID, @RequestBody Option option) {
    option.id = optionID;
    return optionRepository.save(option);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{optionID}")
  public void deleteOptionById(@PathVariable String optionID) {
    optionRepository.delete(optionID);
  }
}
