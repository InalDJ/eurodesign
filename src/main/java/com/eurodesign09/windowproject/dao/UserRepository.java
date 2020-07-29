package com.eurodesign09.windowproject.dao;

import com.eurodesign09.windowproject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username =:username")
    User getUserByName(@Param("username")String username);
}
