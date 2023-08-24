package com.wang.app.rest.Security;

import com.wang.app.rest.Models.Role;
import com.wang.app.rest.Models.UserEntity;
import com.wang.app.rest.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//primary purpose of service is to integrate springsecurity with custom
//data source, usually database, and authenticate users based on
//username and password, assigning roles to authenticated users

//The objects provided by both the CustomUserDetailsService and the SecurityConfig classes are used for authentication and authorization purposes by Spring Security.

@Service
public class CustomUserDetailsService implements UserDetailsService {
//this is a specialized component that acts as a 'getter' function for user details, with extra layer
    //of authentication and authorization
    private UserRepository userRepository;
    //this is used to establish a link to the database
    //The Spring UserRepository interface (presumably extending Spring Data JPA's JpaRepository or a similar interface) defines methods that enable you to perform various database operations related to the UserEntity class.

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //intiialize above
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }//this is userdetails user....
    //takes username as input, uses userRepository to find userentity....representation of app's data model
    //returns userDetails object that spring security uses to perform authentication and auth checks
//password here is fetched from userentity
    //userdetails obejct includes username, pass, mapped roles
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    //purpose of this function: when authenticating user, we need to know not
    //only users name and pass, but also their roles to make authorization decisions...
    //this method transforms role objects to granted authroity objects, which will then be used by springsecurity for authorization
}

//were returning a userdetails...
//we go into database and return userdetails


//UserREPOSITORY extends jpa repo
//you can think of userrepository as a get method with the database
//its basically a librarian