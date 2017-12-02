package com.colorofhope.ayitili.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.uri}")
  String mongoUri;

  @Bean
  public GridFsTemplate gridFsTemplate(MongoClient mongo, MongoConverter mc) throws Exception {
    // if we use the same Mongo instance as the standard mongo factory, we get issues so we create a
    // new SimpleMongoDbFactory using the new client instance
    return new GridFsTemplate(new SimpleMongoDbFactory(new MongoClientURI(mongoUri)), mc);
  }
}
