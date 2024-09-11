package com.niantic.models;

import java.util.ArrayList;

public class Question
{
    private int questionId;
    private int quizId;
    private int questionNumber;
    private String questionText;
    private int selectedAnswerId;

    private ArrayList<Answer> answers = new ArrayList<>();

    public Question()
    {
    }

    public Question(int questionId, int quizId, int questionNumber, String questionText)
    {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.selectedAnswerId = -1;
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public int getQuizId()
    {
        return quizId;
    }

    public void setQuizId(int quizId)
    {
        this.quizId = quizId;
    }

    public int getQuestionNumber()
    {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber)
    {
        this.questionNumber = questionNumber;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public ArrayList<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers)
    {
        this.answers = answers;
    }

    public int getSelectedAnswerId() {
        return selectedAnswerId;
    }

    public void setSelectedAnswerId(int selectedAnswerId) {
        this.selectedAnswerId = selectedAnswerId;
    }
}