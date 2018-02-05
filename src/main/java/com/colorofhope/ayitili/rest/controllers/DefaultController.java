package com.colorofhope.ayitili.rest.controllers;

import com.colorofhope.ayitili.model.DBModel;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api
public abstract class DefaultController<R extends MongoRepository<M, String>, M extends DBModel> {
  R repository;

  @RequestMapping(method = RequestMethod.GET, path = "")
  public List<M> getAll() {
    return repository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, path = "")
  public Object create(M model, String returnPath) throws IOException {
    M newModel = repository.save(model);
    if(returnPath != null && !returnPath.isEmpty()){
      return new RedirectView(returnPath);
    }
    return newModel;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public M getById(@PathVariable String id) {
    return repository.findOne(id);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public Object update(@PathVariable String id, M model, String returnPath) throws IOException {
    model.id = id;
    M existingModel = repository.findOne(id);
    existingModel.merge(model);
    M newModel = repository.save(existingModel);
    if(returnPath != null && !returnPath.isEmpty()){
      return new RedirectView(returnPath);
    }
    return newModel;
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public void delete(@PathVariable String id) {
    repository.delete(id);
  }
}
