package com.wang.app.rest.Controller;


import com.wang.app.rest.Models.Answer;
import com.wang.app.rest.Models.Score;
import com.wang.app.rest.Models.User;
import com.wang.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/submit-answers")
    public ResponseEntity<String> submitAnswers(@RequestBody Map<Integer,Integer > answersData) {
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

        //Saving user to the database
        userRepo.save(user);

        //Set answers for the user
        user.setAnswers(answers);

        //Calculating score based on answers
        Score score = calculateScore(answers);

        //Creating score object and associating it with the user
        user.setScore(score);

        //Return a response to the client
        return ResponseEntity.ok("Answers submitted successfully");

    }

    public Score calculateScore(List<Answer> answers) {
        int score = 0;
        return score;
    }
}
