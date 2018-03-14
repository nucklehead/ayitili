package com.colorofhope.ayitili.repository;

import com.colorofhope.ayitili.model.DBModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DBModelESRepository extends ElasticsearchRepository<DBModel, String> {
    @Query("{\"match_phrase_prefix\" : {\"brand\" : \"?0\"}}")
    Page<DBModel> predictMatch(String text, Pageable pageable);

    @Query("{\"query_string\" : {\"query\" : \"?0\"}}")
    Page<DBModel> findMatched(String text, Pageable pageable);
}
