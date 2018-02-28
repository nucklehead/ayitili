package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Page;
import com.colorofhope.ayitili.repository.DBImageRepository;
import com.colorofhope.ayitili.repository.PageRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
@RequestMapping("/api/page")
public class PageController extends DefaultController<PageRepository, Page> {

  GridFsTemplate gridFsTemplate;

  DBImageRepository dbImageRepository;

  public PageController(PageRepository pageRepository, GridFsTemplate gridFsTemplate, DBImageRepository dbImageRepository) throws IOException {
    this.repository = pageRepository;
    this.dbImageRepository = dbImageRepository;
    this.gridFsTemplate = gridFsTemplate;
    createHomePage();
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{name}/component")
  public Page insertComponent(
      @PathVariable String name, Integer rowIndex, Integer columnIndex, String component) {
    Page page = repository.findByFormatedName(name);
    if (page.bodyRows.size() - 1 < rowIndex) {
      page.getBodyRows().add(Arrays.asList(component));
    } else if (page.bodyRows.get(rowIndex).size() - 1 < columnIndex) {
      page.bodyRows.get(rowIndex).add(component);
    } else {
      page.bodyRows.get(rowIndex).set(columnIndex, component);
    }
    return repository.save(page);
  }

  @Override
  public Object create(Page model, String returnPath) throws IOException {
    String commaReplacer = new String(Base64.encodeBase64(model.formatedName.getBytes()));
    model.bodyRows = model.bodyRows.stream().map(row ->
            row.stream().map(col ->
                    col.replaceAll(commaReplacer, ","))
                    .collect(Collectors.toList()))
            .collect(Collectors.toList());
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formThumbnail);
    model.thumbnail = dbImageRepository.findOne(imageId);
    return super.create(model, returnPath);
  }

  @Override
  public Object update(@PathVariable String id, Page model, String returnPath) throws IOException {
    String commaReplacer = new String(Base64.encodeBase64(model.formatedName.getBytes()));
    model.bodyRows = model.bodyRows.stream().map(row ->
            row.stream().map(col ->
                    col.replaceAll(commaReplacer, ","))
                    .collect(Collectors.toList()))
            .collect(Collectors.toList());
    String imageId = DBImageRepository.addImageTODB(gridFsTemplate, model.formThumbnail);
    model.thumbnail = dbImageRepository.findOne(imageId);
    dbImageRepository.delete(repository.findOne(id).thumbnail.id);
    return super.update(id, model, returnPath);
  }

  @Override
  public void delete(@PathVariable String id){
    dbImageRepository.delete(repository.findOne(id).thumbnail.id);
    super.delete(id);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{id}/activate")
  public void activate(@PathVariable String id) {
    Page page = repository.findOne(id);
    page.active = true;
    repository.save(page);
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{id}/deactivate")
  public void deactivate(@PathVariable String id) {
    Page page = repository.findOne(id);
    page.active = false;
    repository.save(page);
  }

  @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
  void handleBadRequests(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler({IOException.class})
  void handleIOExceptions(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), "Unable to read image file.");
  }

  private void createHomePage() throws IOException {
    if(repository.findByFormatedName("akèy") != null){
      return;
    }
    Page homePage = new Page();
    homePage.name = "Akèy";
    homePage.formatedName = "akèy";
    homePage.active = true;
    File imageFIle = new ClassPathResource("static/img/colorofhop-logo.jpg").getFile();
    DiskFileItem fileItem =  new DiskFileItem("formThumbnail", MediaType.IMAGE_JPEG_VALUE, true, imageFIle.getName(), Math.toIntExact(imageFIle.length()), imageFIle);
    fileItem.getOutputStream();
    homePage.formThumbnail = new CommonsMultipartFile(fileItem);

    create(homePage, null);
  }

}