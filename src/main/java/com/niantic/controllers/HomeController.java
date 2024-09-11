package com.niantic.controllers;

import com.niantic.services.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @Autowired
    private QuizDao quizDao;

    @GetMapping("/")
    public String index(Model model)
    {
        var quizzes = quizDao.getAllQuizzes();

        model.addAttribute("title", "Trivio Home");
        model.addAttribute("quizzes", quizzes);

        return "index";
    }
}