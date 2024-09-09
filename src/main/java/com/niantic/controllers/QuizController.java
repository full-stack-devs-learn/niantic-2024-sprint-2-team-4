package com.niantic.controllers;

import com.niantic.models.Answer;
import com.niantic.models.Question;
import com.niantic.models.Quiz;
import com.niantic.services.AnswerDao;
import com.niantic.services.QuestionDao;
import com.niantic.services.QuizDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public QuizController(QuizDao quizDao, QuestionDao questionDao, AnswerDao answerDao)
    {
        this.quizDao = quizDao;
        this.questionDao = questionDao;
        this.answerDao = answerDao;
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

        //Fetch answers:
        List<Answer> answers = answerDao.getAnswersByQuestionId(firstQuestion.getQuestionId());
        //add quiz data:
        model.addAttribute("quiz", quiz);
        model.addAttribute("totalQuestions", questionCount);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", firstQuestion);
        model.addAttribute("answers", answers);

        return "quiz/start";
    }

    //Fetch next question:
    @GetMapping("/{quizId}/next/{questionId}")
    //@ResponseBody
    public String getNextQuestion(@PathVariable int quizId, @PathVariable int questionId, Model model) {
        Quiz quiz = quizDao.getQuizById(quizId);

        // finds and displays the next question
        Question nextQuestion = questionDao.getNextQuestion(quizId, questionId);

        System.out.println(nextQuestion);

        // counts the total number of questions in the quiz the user is taking
        int totalQuestions = questionDao.getTotalQuestions(quizId);

        // finds all the possible answers to a question
        List<Answer> answers = answerDao.getAnswersByQuestionId(nextQuestion.getQuestionId());

        //Checks if it's the last question:
        boolean isLastQuestion = nextQuestion.getQuestionNumber() == totalQuestions;

        // holds the next question's information
        model.addAttribute("quiz", quiz);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", nextQuestion);
        model.addAttribute("answers", answers);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("isLastQuestion", isLastQuestion);

        // loads the question on the question page in the "start.html" file, where all the questions and answers are loaded into
        return "quiz/start";
    }

    // processes user answers and updates their score
    @PostMapping("/{quizId}/submit")
    public String submitQuiz(@PathVariable int quizId, @RequestParam Map<String, String> userAnswers, Model model, HttpSession session)
    {
        // Retrieve correct answers from database
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);
        Map<Integer, Integer> correctAnswers = new HashMap<>();

        for (Question question : questions)
        {
            List<Answer> correctAnswerList = answerDao.getCorrectAnswersByQuestionId(question.getQuestionId());
            for (Answer answer : correctAnswerList)
            {
                correctAnswers.put(question.getQuestionId(), answer.getAnswerId());
            }
        }

        // Calculate score
        int score = calculateScore(userAnswers, correctAnswers);

        session.setAttribute("score", score);

        return "redirect:/quiz/" + quizId + "/result";      // redirect to results page with score
    }

    // displays the final score
    @GetMapping("/{quizId}/result")
    public String showResult(@PathVariable int quizId, Model model, HttpSession session) {
        // Retrieve score from the session
        Integer score = (Integer) session.getAttribute("score");

        // If score is null, handle it appropriately
        if (score == null)
        {
            score = 0; // Default to 0 if no score is found
        }

        model.addAttribute("score", score);

        // clear from session after displaying score
        session.removeAttribute("score");

        return "quiz/result";
    }

    private int calculateScore(Map<String, String> userAnswers, Map<Integer, Integer> correctAnswers) {
        int score = 0;
        for (Map.Entry<String, String> entry : userAnswers.entrySet())
        {
            int questionId = Integer.parseInt(entry.getKey());
            int selectedAnswerId = Integer.parseInt(entry.getValue());
            if (correctAnswers.get(questionId) != null && correctAnswers.get(questionId) == selectedAnswerId)
            {
                score++;
            }
        }
        return score;
    }
}

    
