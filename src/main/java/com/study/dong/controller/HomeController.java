package com.study.dong.controller;

import org.springframework.ui.Model;

public class HomeController {
    
    public String index(Model model) {
        model.addAttribute("text", "hello22");

        return "test";
    }
}
