package com.indream.noteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.indream.noteservice.model.LabelEntity;

public interface LabelMongoRepository extends MongoRepository<LabelEntity, String>{

}
