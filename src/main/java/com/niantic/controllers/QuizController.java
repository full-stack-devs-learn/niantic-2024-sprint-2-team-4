package com.niantic.controllers;

import com.niantic.models.Question;
import com.niantic.models.Quiz;
import com.niantic.services.QuestionDao;
import com.niantic.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/quiz")
public class QuizController
{
    private QuizDao quizDao;
    private QuestionDao questionDao;

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
        return "quiz";
    }

    //Start the quiz and fetch first question:
    @GetMapping("/{quizId}/start")
    @ResponseBody
    public Question startQuiz(@PathVariable int quizId)
    {
        //Fetch first question
        return questionDao.getFirstQuestion(quizId);
    }

    //Fetch next question:
    @GetMapping("/{quizId}/next/{questionId}")
    @ResponseBody
    public Question getNextQuestion(@PathVariable int quizId, @PathVariable int questionId)
    {
        return questionDao.getNextQuestion(quizId, questionId);
    }
}

    
