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

import java.util.List;

@RestController
public class QuizController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/submit-answers")
    public ResponseEntity<String> submitAnswers(@RequestBody List<Answer> answers) {
        //Create a new user and save it to the database
        User user = new User();

        //Calculating score based on answers
        int score = calculateScore(answers);

        //Set the score for the user

        user.setScore(score);


        //Set answers for the user
        user.setAnswers(answers);

        //Saving user to the database
        userRepo.save(user);

        //Return a response to the client
        return ResponseEntity.ok("Answers submitted successfully");

    }

    public Score calculateScore(List<Answer> answers) {
        int score = 0;
        return score;
    }
}
