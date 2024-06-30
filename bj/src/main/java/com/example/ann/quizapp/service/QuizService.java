package com.example.ann.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ann.quizapp.dao.QuestionDao;
import com.example.ann.quizapp.dao.QuizDao;
import com.example.ann.quizapp.model.Question;
import com.example.ann.quizapp.model.QuestionWrapper;
import com.example.ann.quizapp.model.Quiz;
import com.example.ann.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {

        List<Question> questions = questionDao.findRadomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return ResponseEntity.created(null).body("Quiz created successfully");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for (Question question : questions) {
            questionWrappers.add(new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()));
        }

        return ResponseEntity.ok(questionWrappers);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();

        List<Question> questions = quiz.getQuestions();

        Integer result = 0;
        for (Response response : responses) {
            for (Question question : questions) {
                if (response.getId() == question.getId()) {
                    if (response.getResponse().equals(question.getRightAnswer())) {
                        result++;
                    }
                }
            }
        }
        return ResponseEntity.ok(result);
    }

}
