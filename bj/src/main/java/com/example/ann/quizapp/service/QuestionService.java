package com.example.ann.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ann.quizapp.dao.QuestionDao;
import com.example.ann.quizapp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return ResponseEntity.ok(questionDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {  
        try {
            return ResponseEntity.ok(questionDao.findByCategory(category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return ResponseEntity.created(null).body("Question added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Failed to add question");
    }

}
