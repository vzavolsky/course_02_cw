package com.zavolsky.course_02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JavaQuestionException extends RuntimeException {
    public JavaQuestionException(String s) {
        super(s);
    }
}
