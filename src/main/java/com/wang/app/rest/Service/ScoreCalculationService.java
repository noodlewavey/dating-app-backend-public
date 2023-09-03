package com.wang.app.rest.service;

import com.wang.app.rest.Models.Answer;
import com.wang.app.rest.Models.AnswerInfo;
import com.wang.app.rest.Models.Score;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreCalculationService{

    @Transactional
    public Score calculateScore(List<Answer> answers, Map<Integer,AnswerInfo> questionToMap) {
        int Extraversion = 0;
        int Agreeableness = 0;
        int Conscientiousness = 0;
        int EmotionalStability = 0;
        int Intellect = 0;

        for (Answer answer : answers) {
            int questionId = answer.getQuestionId();
            int answerValue = answer.getAnswerValue();

            if (questionToMap.containsKey(questionId)) {
                AnswerInfo answerInfo = questionToMap.get(questionId);
                //take the answer objec
                int trait = answerInfo.getTrait();
                String valence = answerInfo.getValence();
                if (trait == 1) {
                    if (valence.equals("+")) {
                        Extraversion += answerValue;
                    } else {
                        Extraversion += 6 - answerValue;
                    }
                }
                if (trait == 2) {
                    if (valence.equals("+")) {
                        Agreeableness += answerValue;
                    } else {
                        Agreeableness += 6 - answerValue;
                    }

                }
                if (trait == 3) {
                    if (valence.equals("+")) {
                        Conscientiousness += answerValue;
                    } else {
                        Conscientiousness += 6 - answerValue;
                    }
                }
                if (trait == 4) {
                    if (valence.equals("+")) {
                        EmotionalStability += answerValue;
                    } else {
                        EmotionalStability += 6 - answerValue;
                    }

                }
                if (trait == 5) {
                    if (valence.equals("+")) {
                        Intellect += answerValue;
                    } else {
                        Intellect += 6 - answerValue;
                    }
                }
            }

        }

        Score returnScore = new Score();
        returnScore.setExtraversion(Extraversion);
        returnScore.setConscientiousness(Conscientiousness);
        returnScore.setAgreeableness(Agreeableness);
        returnScore.setIntellect(Intellect);
        returnScore.setEmotionalStability(EmotionalStability);

        return returnScore;

    }
}
