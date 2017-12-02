package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.DBImage;
import com.mongodb.gridfs.GridFSFile;
import java.io.IOException;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public interface DBImageRepository extends MongoRepository<DBImage, String> {
  static String addImageTODB(GridFsTemplate gridFsTemplate, MultipartFile formImage)
      throws IOException {
    if (formImage != null) {
      GridFSFile dbFile =
          gridFsTemplate.store(
              formImage.getInputStream(),
              formImage.getOriginalFilename(),
              formImage.getContentType() == null
                  ? MediaType.IMAGE_JPEG_VALUE
                  : formImage.getContentType());
      return dbFile.getId().toString();
    } else {
      return null;
    }
  }
}
