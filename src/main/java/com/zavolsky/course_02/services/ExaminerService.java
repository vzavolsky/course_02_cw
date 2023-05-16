package com.zavolsky.course_02.services;

import com.zavolsky.course_02.domains.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
