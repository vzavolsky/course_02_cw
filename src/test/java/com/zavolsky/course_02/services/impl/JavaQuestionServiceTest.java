package com.zavolsky.course_02.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.zavolsky.course_02.domains.Question;
import com.zavolsky.course_02.exceptions.JavaQuestionException;
import com.zavolsky.course_02.services.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void afterEach() {
        while (questionService.getAll().size() > 0) {
            questionService.remove(questionService.getRandomQuestion());
        }
    }

    @Test
    public void addTestValues() {
        int sizeBefore = questionService.getAll().size();
        Question expected = new Question("Q1", "A1");
        Question actual = questionService.add("Q1", "A1");

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(sizeBefore + 1);
    }

    @Test
    public void addTestValuesNegative() {
        assertThatExceptionOfType(JavaQuestionException.class)
                .isThrownBy(() -> questionService.add("Question 1", "Answer 1"));
    }

    @Test
    public void addTestObject() {
        int sizeBefore = questionService.getAll().size();
        Question expected = new Question("Q1", "A1");
        Question actual = questionService.add(new Question("Q1", "A1"));

        assertThat(actual).isEqualTo(expected)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(sizeBefore + 1);
    }

    @Test
    public void addTestObjectNegative() {
        assertThatExceptionOfType(JavaQuestionException.class)
                .isThrownBy(() -> questionService.add(new Question("Question 1", "Answer 1")));
    }

    @Test
    public void removeTest() {
        int sizeBefore = questionService.getAll().size();
        Question expected = new Question("Question 1", "Answer 1");
        Question actual = questionService.remove(new Question("Question 1", "Answer 1"));

        assertThat(actual).isEqualTo(expected)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(sizeBefore - 1);
    }

    @Test
    public void removeTestNegative() {
        assertThatExceptionOfType(JavaQuestionException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q1", "A1")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).hasSize(5)
                .containsExactlyInAnyOrder(
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 2", "Answer 2"),
                        new Question("Question 3", "Answer 3"),
                        new Question("Question 4", "Answer 4"),
                        new Question("Question 5", "Answer 5")
                );
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionTestNegative() {
        afterEach();
        assertThatExceptionOfType(JavaQuestionException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }

}
