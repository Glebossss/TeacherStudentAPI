package com.example.demo.dao.model;

import com.example.demo.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "student_table")
public class StudentEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String name;

    private String pictureURL;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
    private List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = new ArrayList<>();

    public StudentEntity(String email, String name, String pictureURL) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public static StudentEntity of(String email, String name, String pictureURL) {
        return new StudentEntity(email, name, pictureURL);
    }

    public StudentDTO toStudentDTO() {
        return StudentDTO.of(id, email, name, pictureURL);
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

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", unconfirmedActivitiesEntities=" + unconfirmedActivitiesEntities +
                '}';
    }
}
