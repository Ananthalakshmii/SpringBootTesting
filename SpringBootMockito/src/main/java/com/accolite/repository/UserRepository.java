package com.accolite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.accolite.model.User;

public interface UserRepository extends MongoRepository<User,Integer>{

	List<User> findByAddress(String address);

}
