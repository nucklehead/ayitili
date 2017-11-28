package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.Banner;
import com.colorofhope.ayitili.model.Comment;
import com.colorofhope.ayitili.model.DBImage;
import com.colorofhope.ayitili.repository.BannerRepository;
import com.colorofhope.ayitili.repository.CommentRepository;
import com.colorofhope.ayitili.repository.DBImageRepository;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class DBImageController extends DefaultController<DBImageRepository, DBImage>{
  public DBImageController(DBImageRepository dbImageRepository) {
    this.repository = dbImageRepository;
  }

  @Autowired
  GridFsTemplate gridFsTemplate;

  @RequestMapping(method = RequestMethod.GET, path = "/{id}.jpg")
  public byte[] getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
    GridFSDBFile image = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    image.writeTo(outputStream);
    response.setContentType(image.getContentType() == null ? MediaType.IMAGE_JPEG_VALUE : image.getContentType() );
    return outputStream.toByteArray();
  }
}
