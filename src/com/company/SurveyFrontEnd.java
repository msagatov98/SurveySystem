package com.company;

import com.company.bean.Answer;
import com.company.bean.Question;
import com.company.bean.User;
import com.company.controller.SurveyController;
import com.company.database.IDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class SurveyFrontEnd {

    private User currentUser;
    private Scanner scanner;
    private SurveyController controller;

    private BufferedReader reader;

    public SurveyFrontEnd(IDB db) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);
        controller = new SurveyController(db);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to My Application");
            System.out.println("Select option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Vote");
            System.out.println("0. Exit");
            System.out.println();

            try {
                System.out.print("Enter option (0-3): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    login();
                } else if (option == 2) {
                    register();
                } else if (option == 3) {
                    vote();
                } else if (option == 0) {
                    break;
                } else {
                    System.out.println("Select right option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

            System.out.println("*************************");
        }
    }

    private void login() {
        System.out.println();
        System.out.println("*****Login*****");

        System.out.print("Enter login: ");
        String login = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User user = controller.login(login, password);

        if (user != null) {
            currentUser = user;
            if (user.isAdmin()) {
                startAsAdmin();
            } else {
                startAsUser();
            }
        } else {
            System.out.println();
            System.out.println("Wrong login or password");
        }

        System.out.println();
    }

    private void startAsAdmin() {

        while (true) {
            System.out.println();
            System.out.println("Welcome Admin");
            System.out.println("Select option:");
            System.out.println("1. Create question");
            System.out.println("2. Get answers");
            System.out.println("0. Logout");
            System.out.println();

            try {
                System.out.print("Enter option (0-2): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    createQuestion();
                } else if (option == 2) {
                    getAnswers();
                } else if (option == 0) {
                    break;
                } else {
                    System.out.println("Select right option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next(); // to ignore incorrect input
            }

            System.out.println("*************************");
        }
    }

    private void startAsUser() {

        while (true) {
            System.out.println();
            System.out.println("Welcome User");
            System.out.println("Select option:");
            System.out.println("1. Vote");
            System.out.println("0. Logout");
            System.out.println();

            try {
                System.out.print("Enter option (0-1): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    vote();
                } else if (option == 0) {
                    break;
                } else {
                    System.out.println("Select right option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next(); // to ignore incorrect input
            }

            System.out.println("*************************");
        }
    }

    private void register() {
        System.out.println();
        System.out.println("*****Register*****");

        System.out.print("Enter login: ");
        String login = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User user = controller.register(login, password);

        if (user != null) {
            System.out.println("User registered successfully");
            currentUser = user;
            startAsUser();
        } else {
            System.out.println();
            System.out.println("Registration error");
        }

        System.out.println();
    }

    private void vote() {
        List<Question> list;

        list = controller.getQuestions();

        if (list != null) {

            if (list.isEmpty()) {
                System.out.println("No question");

            } else {
                System.out.println();

                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i).getQuestion());
                }

                System.out.println();
                System.out.print("Select question from 1-" + list.size() + ": ");

                int selectedQuestion = scanner.nextInt();

                if (selectedQuestion <= list.size()) {
                    int questionId = list.get(selectedQuestion).getId();
                    Question question = controller.getQuestion(questionId);
                    System.out.println();

                    if (question != null) {
                        System.out.println(question.getQuestion());
                        System.out.println("Select option (1-4)");
                        System.out.println(question.getOption1());
                        System.out.println(question.getOption1());
                        System.out.println(question.getOption1());
                        System.out.println(question.getOption1());

                        int option = scanner.nextInt();


                        if (0 < option && option < 5) {
                            String answer = "";

                            switch (option) {
                                case 1:
                                    answer = question.getOption1();
                                    break;
                                case 2:
                                    answer = question.getOption2();
                                    break;
                                case 3:
                                    answer = question.getOption3();
                                    break;
                                case 4:
                                    answer = question.getOption4();
                                    break;
                            }

                            Answer newAnswer = new Answer(currentUser.getId(), question.getId(), answer);

                            if (controller.createAnswer(newAnswer)) {
                                System.out.println("Your answer saved");
                            } else {
                                System.out.println("Some error");
                            }
                        } else {
                            System.out.println();
                            System.out.println("Select right option");
                        }
                    } else {
                        System.out.println("Some error");
                    }
                } else {
                    System.out.println("Select right question");
                }
            }
        }
    }

    private void createQuestion() throws IOException {
        System.out.println("*****Create question*****");

        System.out.print("Enter question: ");
        String question = reader.readLine();
        System.out.print("Enter answer: ");
        String answer = reader.readLine();
        System.out.print("Enter option1: ");
        String option1 = reader.readLine();
        System.out.print("Enter option2: ");
        String option2 = reader.readLine();
        System.out.print("Enter option3: ");
        String option3 = reader.readLine();
        System.out.print("Enter option4: ");
        String option4 = reader.readLine();

        Question question1 = new Question(question, answer, option1, option2, option3, option4);
        controller.createQuestion(question1);
    }

    private void getAnswers() {
        List<Answer> answers;
        answers = controller.getAnswers();

        if (answers != null) {
            System.out.println();

            if (answers.isEmpty()) {
                System.out.println("No answer");
            } else {
                System.out.println("*****Answers*****");
                List<Question> questions = controller.getQuestions();
                for (Question question : questions) {
                    int count = 0;
                    for (Answer answer : answers) {
                        if (question.getAnswer().equals(answer.getAnswer()))
                            count++;
                    }
                    System.out.println("For question \"" + question.getQuestion() + "\" there are " + count + " answers");
                }
            }
        } else {
            System.out.println("Some error");
        }
    }
}
