package com.pro.sky.java.course2.homeworkemployeebook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "InvalidEmployeeName")
public class InvalidEmployeeNameException extends RuntimeException{
    public InvalidEmployeeNameException(String s){
        super();
    }
}
