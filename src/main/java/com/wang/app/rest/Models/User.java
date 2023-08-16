package com.wang.app.rest.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
//lets us know user class will be table in database
public class User {
    //this reads ,creates, update

    @Id
    //this tells mySQL that id will be uinque for each uer
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Column
    //each attribute will be column in database
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String occupation;

    //this id attribute is a primary key in database

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        //this is a setter method
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
