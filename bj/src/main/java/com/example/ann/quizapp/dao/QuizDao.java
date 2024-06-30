package com.example.ann.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ann.quizapp.model.Quiz;
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
