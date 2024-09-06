package com.niantic.controllers;

import com.niantic.models.Answer;
import com.niantic.models.Question;
import com.niantic.models.Quiz;
import com.niantic.services.AnswerDao;
import com.niantic.services.QuestionDao;
import com.niantic.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz")
public class QuizController
{
    private QuizDao quizDao;
    private QuestionDao questionDao;
    private AnswerDao answerDao;

    @Autowired
    public QuizController(QuizDao quizDao, QuestionDao questionDao)
    {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }

    //Method to handle quiz selection and display quiz page:
    @GetMapping("/{quizId}")
    public String showQuiz(@PathVariable int quizId, Model model)
    {
        //get quiz data:
        Quiz quiz = quizDao.getQuizById(quizId);

        //add quiz data:
        model.addAttribute("quiz", quiz);

        //return quiz page:
        return "quiz/index";
    }

    //Start the quiz and fetch first question:
    @GetMapping("/{quizId}/start")
    public String startQuiz(@PathVariable int quizId, Model model)
    {
        //get quiz data:
        Quiz quiz = quizDao.getQuizById(quizId);

        //Fetch first question
        Question firstQuestion = questionDao.getFirstQuestion(quizId);
        int questionCount = questionDao.getTotalQuestions(quizId);
        //add quiz data:
        model.addAttribute("quiz", quiz);
        model.addAttribute("totalQuestions", questionCount);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", firstQuestion);

        return "quiz/start";
    }

    //Fetch next question:
    @GetMapping("/{quizId}/next/{questionId}")
    @ResponseBody
    public String getNextQuestion(@PathVariable int quizId, @PathVariable int questionId) {
        Question nextQuestion = questionDao.getNextQuestion(quizId, questionId);
        int totalQuestions = questionDao.getTotalQuestions(quizId);

        // format response as a string
        return  nextQuestion.getQuestionText() + "\n" +
                nextQuestion.getQuestionNumber() + "\n" +
                totalQuestions + "\n" +
                nextQuestion.getQuestionId();
    }
}

    
