package com.niantic.models;

import java.util.ArrayList;

public class Quiz {
    private int quizId;
    private String title;
    private boolean isLive;
    private String description;

    private ArrayList<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(int quizId, String title, boolean isLive, String description) {
        this.quizId = quizId;
        this.title = title;
        this.isLive = isLive;
        this.description = description;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int calculateScore() {
        int score = 0;

        for (Question question : questions) {
            int selectedAnswerId = question.getSelectedAnswerId();

            for (Answer answer : question.getAnswers()) {
                if (answer.getAnswerId() == selectedAnswerId && answer.isCorrect()) {
                    score++;
                    break;
                }
            }
        }
        return score;
    }
}