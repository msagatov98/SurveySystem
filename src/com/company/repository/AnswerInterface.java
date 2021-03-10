package com.company.repository;

import com.company.bean.Answer;

import java.util.List;

public interface AnswerInterface {
    List<Answer> getAllAnswers();
    boolean createAnswer(Answer answer);
}