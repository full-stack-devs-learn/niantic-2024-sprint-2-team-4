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

import java.util.HashMap;
import java.util.Map;

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
        return "quiz/index";
    }

    //Start the quiz and fetch first question:
    @GetMapping("/{quizId}/start")
    public String startQuiz(@PathVariable int quizId, Model model)
    {
        //Fetch first question
        Question firstQuestion = questionDao.getFirstQuestion(quizId);

        model.addAttribute("quizId", quizId);
        model.addAttribute("question", firstQuestion);

        return "quiz/start";
    }

    @GetMapping("/{quizId}/details")
    @ResponseBody
    public Map<String, Object> getQuizDetails(@PathVariable int quizId) {
        Map<String, Object> details = new HashMap<>();
        Quiz quiz = quizDao.getQuizById(quizId);
        int totalQuestions = questionDao.getTotalQuestions(quizId);

        details.put("quiz", quiz);
        details.put("totalQuestions", totalQuestions);

        return details;
    }

    //Fetch next question:
    @GetMapping("/{quizId}/next/{questionId}")
    @ResponseBody
    public String getNextQuestion(@PathVariable int quizId, @PathVariable int questionId) {
        Question nextQuestion = questionDao.getNextQuestion(quizId, questionId);
        int totalQuestions = questionDao.getTotalQuestions(quizId);

        // Construct a plain text response or HTML if needed
        StringBuilder response = new StringBuilder();
        response.append(nextQuestion.getQuestionText()).append("\n");
        response.append(nextQuestion.getQuestionNumber()).append("\n");
        response.append(totalQuestions).append("\n");
        response.append(nextQuestion.getQuestionId()); // Include next question ID

        return response.toString();
    }
}

    
