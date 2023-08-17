package com.wang.app.rest.Models;

import jakarta.persistence.*;

@Entity

public class Score {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    //one to one means one score belongs to one user...each score assocaited with single user
    //joincolumn means foreign key column name user_id references primary key of user table

    @Column
    private int Extraversion;

    @Column
    private int Agreeableness;

    private int Conscientiousness;

    private int EmotionalStability;

    private int Intellect;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getExtraversion() {
        return Extraversion;
    }

    public void setExtraversion(int extraversion) {
        Extraversion = extraversion;
    }

    public int getAgreeableness() {
        return Agreeableness;
    }

    public void setAgreeableness(int agreeableness) {
        Agreeableness = agreeableness;
    }

    public int getConscientiousness() {
        return Conscientiousness;
    }

    public void setConscientiousness(int conscientiousness) {
        Conscientiousness = conscientiousness;
    }

    public int getEmotionalStability() {
        return EmotionalStability;
    }

    public void setEmotionalStability(int emotionalStability) {
        EmotionalStability = emotionalStability;
    }
    public int getIntellect() {
        return Intellect;
    }

    public void setIntellect(int intellect) {
        Intellect = intellect;
    }
}
