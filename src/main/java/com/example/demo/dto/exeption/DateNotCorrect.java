package com.example.demo.dto.exeption;

public class DateNotCorrect extends Exception {

    @Override
    public String toString() {
        return "Error. Date Not Correct";
    }
}