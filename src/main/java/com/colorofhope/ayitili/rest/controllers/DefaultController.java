package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.DBModel;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

@Api
public abstract class DefaultController<R extends MongoRepository<M, String>, M extends DBModel> {
  R repository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<M> getAll() {
    return repository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public M create(M model) throws IOException {
    return repository.save(model);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public M getById(@PathVariable String id) {
    return repository.findOne(id);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public M update(@PathVariable String id, M model) throws IOException {
    model.id = id;
    return repository.save(model);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public void delete(@PathVariable String accountID) {
    repository.delete(accountID);
  }
}
