package com.company;

import com.company.database.IDB;
import com.company.database.SurveyDatabase;

public class Main {
    public static void main(String[] args) {

        IDB db = new SurveyDatabase();
        SurveyFrontEnd frontEnd = new SurveyFrontEnd(db);
        frontEnd.start();

    }
}
