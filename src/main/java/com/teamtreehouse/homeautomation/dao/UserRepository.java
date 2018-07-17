package com.teamtreehouse.homeautomation.dao;

import com.teamtreehouse.homeautomation.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = true)
public interface UserRepository extends CrudRepository<User, Long> {
  User findByName(String name);


}