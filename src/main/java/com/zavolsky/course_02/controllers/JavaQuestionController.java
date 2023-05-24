package com.zavolsky.course_02.controllers;

import com.zavolsky.course_02.domains.Question;
import com.zavolsky.course_02.services.impl.JavaQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {

    private final JavaQuestionService questions;

    public JavaQuestionController(JavaQuestionService questions) {
        this.questions = questions;
    }

    @GetMapping(path = "java/")
    public Collection<Question> getAll() {
        return questions.getAll();
    }

    @GetMapping(path = "/java/add")
    public Question addQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        return questions.add(question, answer);
    }

    @GetMapping(path = "/java/remove")
    public Question removeQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        return questions.remove(new Question(question, answer));
    }

    @GetMapping(path = "/java/find")
    public Question findQuestion(
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        return questions.find(question, answer);
    }
}
