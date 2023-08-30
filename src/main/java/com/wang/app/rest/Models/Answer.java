package com.wang.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int questionId;
    @Column
    private int answerValue;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity user;
    //many to one: many answers belong to single user
    //joincolumn: this is the foreign key in answer table that references primary key of user table


    public Answer(int questionId, int answerValue) {
        this.questionId = questionId;
        this.answerValue = answerValue;
    }

    public Answer() {

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity () {
        return user;
    }

    public void setUserEntity(UserEntity user) {
        this.user = user;
    }
}

