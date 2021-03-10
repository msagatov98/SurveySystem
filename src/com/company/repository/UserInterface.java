package com.company.repository;

import com.company.bean.Question;
import com.company.bean.User;

import java.util.List;

public interface UserInterface {

    User login(String name, String password);

    User register(String name, String password);

}