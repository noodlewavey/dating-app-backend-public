package com.wang.app.rest.Repo;

import com.wang.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    //this is a interface that extends JpaRepository

}
