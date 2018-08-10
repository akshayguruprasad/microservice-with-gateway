package com.indream.noteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.indream.noteservice.model.NoteEntity;

@Repository
public interface NoteMongoRepository extends MongoRepository<NoteEntity, String> {

}
