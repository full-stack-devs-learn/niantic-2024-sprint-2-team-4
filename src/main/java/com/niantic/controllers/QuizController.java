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
import java.util.ArrayList;
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
        Quiz quiz = quizDao.getQuizById(quizId);
        model.addAttribute("quiz", quiz);
        return "quiz/index";
    }

    //Start the quiz and fetch first question:
    @GetMapping("/{quizId}/start")
    public String startQuiz(@PathVariable int quizId, Model model)
    {
        Quiz quiz = quizDao.getQuizById(quizId);
        Question firstQuestion = questionDao.getFirstQuestion(quizId);
        int questionCount = questionDao.getTotalQuestions(quizId);

        List<Answer> answers = answerDao.getAnswersByQuestionId(firstQuestion.getQuestionId());

        model.addAttribute("quiz", quiz);
        model.addAttribute("totalQuestions", questionCount);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", firstQuestion);
        model.addAttribute("answers", answers);

        return "quiz/start";
    }

    //Fetch next question:
    @GetMapping("/{quizId}/next/{questionId}")
    public String getNextQuestion(@PathVariable int quizId, @PathVariable int questionId, Model model) {
        Quiz quiz = quizDao.getQuizById(quizId);
        Question nextQuestion = questionDao.getNextQuestion(quizId, questionId);
        int totalQuestions = questionDao.getTotalQuestions(quizId);

        System.out.println(nextQuestion);

        // finds all the possible answers to a question
        List<Answer> answers = answerDao.getAnswersByQuestionId(nextQuestion.getQuestionId());

        // looks for last question
        boolean isLastQuestion = nextQuestion.getQuestionNumber() == totalQuestions;

        model.addAttribute("quiz", quiz);
        model.addAttribute("quizId", quizId);
        model.addAttribute("question", nextQuestion);
        model.addAttribute("answers", answers);
        model.addAttribute("totalQuestions", totalQuestions);
        model.addAttribute("isLastQuestion", isLastQuestion);

        return "quiz/question-fragment";
    }

    // holds all the correct answers for each quiz
    @GetMapping("/{quizId}/correct-answers")
    @ResponseBody
    public Map<Integer, Integer> getCorrectAnswers(@PathVariable int quizId) {
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);
        Map<Integer, Integer> correctAnswersMap = new HashMap<>();

        for (Question question : questions) {
            List<Answer> correctAnswers = answerDao.getCorrectAnswersByQuestionId(question.getQuestionId());
            if (!correctAnswers.isEmpty()) {
                correctAnswersMap.put(question.getQuestionId(), correctAnswers.get(0).getAnswerId());
            }
        }

        return correctAnswersMap;
    }


    // displays the final score
    @GetMapping("/{quizId}/result")
    public String showResult(@PathVariable int quizId, Model model, HttpSession session) {
        Integer score = (Integer) session.getAttribute("score");

        if (score == null)
        {
            score = 0;
        }

        // Retrieve correct answers from each question:
        List<Question> questions = questionDao.getQuestionsByQuizId(quizId);
        List<Answer> correctAnswers = new ArrayList<>();

        for (Question question : questions)
        {
            List<Answer> correctAnswerList = answerDao.getCorrectAnswersByQuestionId(question.getQuestionId());
            correctAnswers.addAll(correctAnswerList);
        }

        model.addAttribute("score", score);
        model.addAttribute("correctAnswers", correctAnswers);

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