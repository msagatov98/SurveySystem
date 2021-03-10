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
        Connection con = null;
        try {
        con = db.getConnection();
        String sql = "SELECT * FROM answer";
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);
        List<Answer> questions = new ArrayList<>();
        while (rs.next()) {
            Answer question = new Answer(
                    rs.getInt("userid"),
                    rs.getInt("questionid"),
                    rs.getString("answer")
            );

            questions.add(question);
        }

        return questions;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
        return null;
    }

    @Override
    public boolean createAnswer(Answer answer) {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql = "INSERT INTO answer(userid, questionid, answer) " +
                    "VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, answer.getUserId());
            st.setInt(2, answer.getQuestionId());
            st.setString(3, answer.getAnswer());

            return st.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }
}
