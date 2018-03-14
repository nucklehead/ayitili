package com.colorofhope.ayitili.config;

import com.colorofhope.ayitili.model.DBModel;
import com.colorofhope.ayitili.repository.DBModelESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticSearchSync implements CommandLineRunner {

    @Autowired
    DBModelESRepository dbModelESRepository;

    @Autowired
    private List<MongoRepository<? extends DBModel, String>> repositories;


    @Override
    public void run(String... strings) throws Exception {
        repositories.forEach(repository -> {
            repository.findAll().forEach(model -> {
//                TODO: why is this not persisting after you do find all
                model.id = null;
                dbModelESRepository.save(model);
            });
        });
    }
}
