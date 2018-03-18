package com.colorofhope.ayitili.model;

import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fs.files")
public class DBImage extends DBModel {
  public String filename;
  public long length;
  public String contentType;
  public long chunkSize;
  public Date uploadDate;
  public String md5;

  public DBImage() {
  }

  public DBImage(
      String filename,
      long length,
      String contentType,
      long chunkSize,
      Date uploadDate,
      String md5) {
    this.filename = filename;
    this.length = length;
    this.contentType = contentType;
    this.chunkSize = chunkSize;
    this.uploadDate = uploadDate;
    this.md5 = md5;
  }

  @Override
  public String toString() {
    return id;
  }
}
