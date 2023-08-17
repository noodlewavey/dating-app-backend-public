package com.wang.app.rest.Models;

public class AnswerInfo {
    private int trait;
    private String valence;

    public AnswerInfo(String trait, String valence) {
        this.trait = trait;
        this.valence = valence;
    }

    public int getTrait() {
        return trait;
    }

    public String getValence() {
        return valence;
    }
}

}
