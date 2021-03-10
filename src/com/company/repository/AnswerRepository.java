package com.company.repository;

import com.company.bean.Answer;
import com.company.bean.Question;
import com.company.database.IDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository implements AnswerInterface {

    private IDB db;

    public AnswerRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<Answer> getAllAnswers() {
        // TODO
        return null;
    }

    @Override
    public boolean createAnswer(Answer answer) {
        // TODO
        return false;
    }
}
