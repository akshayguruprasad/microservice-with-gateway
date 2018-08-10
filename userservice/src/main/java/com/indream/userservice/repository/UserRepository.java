package com.indream.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.indream.userservice.model.UserEntity;

/**
 * user custom repository method
 * 
 * @author Akshay
 *
 */
@Repository("userMongo")
public interface UserRepository extends MongoRepository<UserEntity, String> {
	Optional<UserEntity> getByEmail(String email);
}