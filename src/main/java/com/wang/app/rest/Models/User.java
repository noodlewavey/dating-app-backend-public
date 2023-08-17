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
    //one to many: one user can have multiple answers
    //cascade setting ensures changes to user will be cascaded to answers

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Score score;
    //one to one: one user can have one score at most,,,
    //cascade setting ensures changes to user will be cascaded to score

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

    public void setAnswers(List<Answer> answers) {
        if (answers == null || answers.size() != 50) {
            throw new IllegalArgumentException("Answers list must have 50 answers.");
        }
        this.answers = answers;
    }
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
