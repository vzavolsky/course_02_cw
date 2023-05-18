package com.zavolsky.course_02.services.impl;

import com.zavolsky.course_02.domains.Question;
import com.zavolsky.course_02.exceptions.JavaQuestionException;
import com.zavolsky.course_02.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQ = new Question(question, answer);
        questions.add(newQ);
        return newQ;
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new JavaQuestionException("Question already exists.");
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new JavaQuestionException("Question is not found.");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.stream()
                .skip(questions.isEmpty() ? 0 : random.nextInt(questions.size()))
                .findFirst()
                .orElseThrow(() -> new JavaQuestionException("Question is not found."));
    }

    public Question find(String question, String answer) {
        return questions.stream()
                .filter(ticket -> ticket.getQuestion().equals(question) && ticket.getAnswer().equals(answer))
                .findFirst()
                .orElseThrow(() -> new JavaQuestionException("Nothing was found."));
    }
}
