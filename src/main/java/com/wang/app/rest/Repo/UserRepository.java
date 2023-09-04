package com.wang.app.rest.Repo;

import com.wang.app.rest.Models.User;
import com.wang.app.rest.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
        //this is a query method that will be used to find a user by username...
    Boolean existsByUsername(String username);
        //this is a query method that will be used to check if a user exists by username...
}
