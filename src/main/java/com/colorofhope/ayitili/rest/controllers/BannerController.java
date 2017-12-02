package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Banner;
import com.colorofhope.ayitili.repository.BannerRepository;
import com.colorofhope.ayitili.repository.DBImageRepository;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banner")
public class BannerController extends DefaultController<BannerRepository, Banner> {

  @Autowired GridFsTemplate gridFsTemplate;

  @Autowired DBImageRepository dbImageRepository;

  public BannerController(BannerRepository bannerRepository) {
    this.repository = bannerRepository;
  }

  @Override
  public Banner create(Banner model) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.create(model);
  }

  @Override
  public Banner update(@PathVariable String id, Banner model) throws IOException {
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formImage);
    model.image = dbImageRepository.findOne(imageId);
    return super.update(id, model);
  }

  @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
  void handleBadRequests(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler({IOException.class})
  void handleIOExceptions(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), "Unable to read image file.");
  }
}
