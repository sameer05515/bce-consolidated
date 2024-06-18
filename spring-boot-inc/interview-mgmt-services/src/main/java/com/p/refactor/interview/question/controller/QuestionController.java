package com.p.refactor.interview.question.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @GetMapping("/")
    public String index() {
        return "Hello, this is the root endpoint!";
    }
}
