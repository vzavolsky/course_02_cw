package com.zavolsky.course_02.services.impl;

import com.zavolsky.course_02.domains.Question;
import com.zavolsky.course_02.exceptions.JavaQuestionException;
import com.zavolsky.course_02.services.ExaminerService;
import com.zavolsky.course_02.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <=0 || amount > questionService.getAll().size()) {
            throw new JavaQuestionException("Incorrect amount.");
        }
        Set<Question> questions = new HashSet<>(amount);
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
