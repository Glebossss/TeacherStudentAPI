package com.example.demo.dto.exeption;

public class AccessNotSuccessful extends Exception {

    @Override
    public String toString() {
        return "Error. Access not successful";
    }
}
