package com.wang.app.rest.Controller;


import com.wang.app.rest.Models.Answer;
import com.wang.app.rest.Models.AnswerInfo;
import com.wang.app.rest.Models.Score;
import com.wang.app.rest.Models.User;
import com.wang.app.rest.Repo.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {

    @Autowired
    private UserRepo userRepo;

    private Map<Integer, AnswerInfo> questionToMap = new HashMap<>();

    //(1) Extraversion, (2) Agreeableness, (3) Conscientiousness, (4) Emotional Stability, or (5) Intellect/Imagination)
    // and its direction of scoring (+ or -). These numbers should not be included in the actual survey questionnaire. For further information on scoring IPIP scales, click the following link:

    //keys in the hash map are the question numbers, values are the answer info objects
    @PostConstruct
    //this postConstruct ensures questionToMap is initialized as soon as controller is created, before requests made
    public void initQuestionToMap() {
        questionToMap.put(1, new AnswerInfo(1, "+"));
        questionToMap.put(2, new AnswerInfo(2, "-"));
        questionToMap.put(3, new AnswerInfo(3, "+"));
        questionToMap.put(4, new AnswerInfo(4, "-"));
        questionToMap.put(5, new AnswerInfo(5, "+"));
        questionToMap.put(6, new AnswerInfo(1, "-"));
        questionToMap.put(7, new AnswerInfo(2, "+"));
        questionToMap.put(8, new AnswerInfo(3, "-"));
        questionToMap.put(9, new AnswerInfo(4, "+"));
        questionToMap.put(10, new AnswerInfo(5, "-"));
        questionToMap.put(11, new AnswerInfo(1, "+"));
        questionToMap.put(12, new AnswerInfo(2, "-"));
        questionToMap.put(13, new AnswerInfo(3, "+"));
        questionToMap.put(14, new AnswerInfo(4, "-"));
        questionToMap.put(15, new AnswerInfo(5, "+"));
        questionToMap.put(16, new AnswerInfo(1, "-"));
        questionToMap.put(17, new AnswerInfo(2, "+"));
        questionToMap.put(18, new AnswerInfo(3, "-"));
        questionToMap.put(19, new AnswerInfo(4, "+"));
        questionToMap.put(20, new AnswerInfo(5, "-"));
        questionToMap.put(21, new AnswerInfo(1, "+"));
        questionToMap.put(22, new AnswerInfo(2, "-"));
        questionToMap.put(23, new AnswerInfo(3, "+"));
        questionToMap.put(24, new AnswerInfo(4, "-"));
        questionToMap.put(25, new AnswerInfo(5, "+"));
        questionToMap.put(26, new AnswerInfo(1, "-"));
        questionToMap.put(27, new AnswerInfo(2, "+"));
        questionToMap.put(28, new AnswerInfo(3, "-"));
        questionToMap.put(29, new AnswerInfo(4, "+"));
        questionToMap.put(30, new AnswerInfo(5, "-"));
        questionToMap.put(31, new AnswerInfo(1, "+"));
        questionToMap.put(32, new AnswerInfo(2, "-"));
        questionToMap.put(33, new AnswerInfo(3, "+"));
        questionToMap.put(34, new AnswerInfo(4, "-"));
        questionToMap.put(35, new AnswerInfo(5, "+"));
        questionToMap.put(36, new AnswerInfo(1, "-"));
        questionToMap.put(37, new AnswerInfo(2, "+"));
        questionToMap.put(38, new AnswerInfo(3, "-"));
        questionToMap.put(39, new AnswerInfo(4, "+"));
        questionToMap.put(40, new AnswerInfo(5, "-"));
        questionToMap.put(41, new AnswerInfo(1, "+"));
        questionToMap.put(42, new AnswerInfo(2, "-"));
        questionToMap.put(43, new AnswerInfo(3, "+"));
        questionToMap.put(44, new AnswerInfo(4, "-"));
        questionToMap.put(45, new AnswerInfo(5, "+"));
        questionToMap.put(46, new AnswerInfo(1, "-"));
        questionToMap.put(47, new AnswerInfo(2, "+"));
        questionToMap.put(48, new AnswerInfo(3, "-"));
        questionToMap.put(49, new AnswerInfo(4, "+"));
        questionToMap.put(50, new AnswerInfo(5, "-"));
    }
    //WHY IS THE return type map and not Hashmap

    @PostMapping("/submit-answers")
    public ResponseEntity<String> submitAnswers(@RequestBody Map<Integer, Integer> answersData) {
        //Create a new user and save it to the database
        User user = new User();

        //the input array must have keys and values
        //example of input array: {1:2, 2:4, 3:1}
        //left is question ID, right is answer value
        //this would be JSON, the keys and values are used to represent properties and values of an object 

        //Creating answer objects and associating them with the user
        List<Answer> answers = new ArrayList<>();
        //looping over array of answers and creating answer objects
        for (Map.Entry<Integer, Integer> entry : answersData.entrySet()) {
            Integer questionId = entry.getKey();
            Integer answerValue = entry.getValue();

            Answer answer = new Answer(questionId, answerValue);
            answer.setUser(user); // Associate the answer with the user
            answers.add(answer);  // Add the answer to the answers list
        }


        //Set answers for the user
        user.setAnswers(answers);

        //Calculating score based on answers
        Score score = calculateScore(answers);

        //Creating score object and associating it with the user
        user.setScore(score);

        //Saving user to the database
        userRepo.save(user);

        //Return a response to the client
        return ResponseEntity.ok("Answers submitted successfully, here is the score for the user: " + score.toString() + "");

    }


    private Score calculateScore(List<Answer> answers) {
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
