package com.example.demo.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentDTO {

    private Long id;

    private String email;

    private String name;

    private String pictureURL;

    public StudentDTO(Long id, String email, String name, String pictureURL) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public static StudentDTO of(Long id, String email, String name, String pictureURL) {
        return new StudentDTO(id, email, name, pictureURL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
