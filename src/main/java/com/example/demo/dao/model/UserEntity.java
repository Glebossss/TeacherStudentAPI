package com.example.demo.dao.model;

import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.dto.UserDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private String pictureURL;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    public UserEntity(String email, String name, String pictureURL, RoleUser roleUser) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.role = roleUser;
    }

    public static UserEntity of(String email, String name, String pictureURL, RoleUser roleUser) {
        return new UserEntity(email, name, pictureURL, roleUser);
    }

    public UserDTO toAllUserDTO() {
        return UserDTO.of(id, email, name, pictureURL, role);
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
