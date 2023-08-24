package com.wang.app.rest.Models;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Table(name = "users")
@Data
@NoArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //want roles to be shown
    //fetchtype eager: when we get user, we want to get roles as well
    //cascade type all: if we delete user, we want to delete roles as well
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}

//JOIN TABLE:  tracks which users have which roles
//RBAC involves associating users with certain roles, and each role is granted specific permissions. This helps control who can access what functionality and data within an application.