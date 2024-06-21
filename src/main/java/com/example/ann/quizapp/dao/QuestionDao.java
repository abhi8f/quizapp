package com.example.ann.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ann.quizapp.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

}
