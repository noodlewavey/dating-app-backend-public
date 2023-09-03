package com.wang.app.rest.Controller;

import com.wang.app.rest.Models.User;
import com.wang.app.rest.Models.UserEntity;
import com.wang.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value="/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value="/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    //this will return all users from database
    }

    @PostMapping(value="/save")
    public String saveUser(@RequestBody User user){
        UserEntity userEntity = new UserEntity();
        user.setUserEntity(userEntity);
        userEntity.setUser(user);
        userRepo.save(user);
        return "User saved";
    }

    @PutMapping(value="/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());
        userRepo.save(updatedUser);
        return "User updated";
    }

    @DeleteMapping (value = "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        return "User deleted WITH THE ID: " + id;
    }
}


//this tells IDE its a rest controller and we have some kind of endpoints in here;