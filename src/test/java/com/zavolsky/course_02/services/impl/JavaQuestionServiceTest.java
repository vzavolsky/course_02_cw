package com.zavolsky.course_02.services.impl;

import com.zavolsky.course_02.domains.Question;
import com.zavolsky.course_02.services.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add("Question 1", "Answer 1");
        questionService.add("Question 2", "Answer 2");
        questionService.add("Question 3", "Answer 3");
        questionService.add("Question 4", "Answer 4");
        questionService.add("Question 5", "Answer 5");
    }

    @AfterEach
    public void afterEach () {
        questionService.getAll().forEach(questionService::remove);
    }

    public void addTest() {
        int sizeBefore = questionService.getAll().size();
        Question expected = new Question("Q1", "A1");
        Question actual = questionService.add("Q1", "A1");


    }

}
