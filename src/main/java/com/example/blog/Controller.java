package com.example.blog;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String home (Model model) {
model.addAttribute("title","Главная страница");
        return "home";
    }

}
