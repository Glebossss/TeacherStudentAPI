package com.example.demo.dto;


import com.example.demo.dao.model.enums.RoleUser;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private String pictureURL;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    public UserDTO(Long id, String email, String name, String pictureURL, RoleUser roleUser) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.role = roleUser;
    }

    public static UserDTO of(Long id, String email, String name, String pictureURL, RoleUser roleUser) {
        return new UserDTO(id, email, name, pictureURL, roleUser);
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

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }
}
