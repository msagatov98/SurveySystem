package com.company.controller;

import com.company.bean.Answer;
import com.company.bean.Question;
import com.company.bean.User;
import com.company.database.IDB;
import com.company.repository.*;

import java.util.ArrayList;
import java.util.List;

public class SurveyController {

    private UserRepository userRepository;
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public SurveyController(IDB db) {
        userRepository = new UserRepository(db);
        answerRepository = new AnswerRepository(db);
        questionRepository = new QuestionRepository(db);
    }

    public User login(String name, String password) {
        return userRepository.login(name, password);
    }

    public User register(String name, String password) {
        return userRepository.register(name, password);
    }

    public boolean createAnswer(Answer answer) {
        return answerRepository.createAnswer(answer);
    }

    public List<Answer> getAnswers() {
        return answerRepository.getAllAnswers();
    }

    public boolean createQuestion(Question question) {
        return questionRepository.createQuestion(question);
    }

    public Question getQuestion(int id) {
        return questionRepository.getQuestion(id);
    }

    public ArrayList<Question> getQuestions() {
        return questionRepository.getAllQuestions();
    }
}
