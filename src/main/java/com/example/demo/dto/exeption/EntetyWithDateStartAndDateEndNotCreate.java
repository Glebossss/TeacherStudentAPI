package com.example.demo.dto.exeption;

public class EntetyWithDateStartAndDateEndNotCreate extends Exception {

    @Override
    public String toString() {
        return "Error. Entity not create";
    }

}
