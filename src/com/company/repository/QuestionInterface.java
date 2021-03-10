package com.company.repository;

import com.company.bean.Question;

import java.util.List;

public interface QuestionInterface {
    Question getQuestion(int id);
    List<Question> getAllQuestions();
    boolean createQuestion(Question question);
}