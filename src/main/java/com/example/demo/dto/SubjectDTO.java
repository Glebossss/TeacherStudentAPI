package com.example.demo.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SubjectDTO {

    private String name;

    public SubjectDTO(String name) {

        this.name = name;
    }

    public static SubjectDTO of(String name) {
        return new SubjectDTO(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
