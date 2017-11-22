package com.aigleinfo.siksepounoutout.web;

import com.aigleinfo.siksepounoutout.rest.controllers.OptionController;
import com.aigleinfo.siksepounoutout.rest.controllers.VideoController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api
public class AppController {

  @Autowired OptionController optionController;

  @Autowired VideoController videoController;

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public String index(Model model) {
    model.addAttribute("name", "JP");
    model.addAttribute("title", "Home Page");
    model.addAttribute("optionTitle", "Opsyon ki genyen yo:");

    model.addAttribute("options", optionController.getOptions());
    model.addAttribute("fbVideos", videoController.getFacebookVideos());
    model.addAttribute("pageVideoPath", "Aigleinfo/videos");

    return "home";
  }
}
