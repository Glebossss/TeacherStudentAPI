package com.example.demo.dto;


import com.example.demo.dao.model.SubjectEntity;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class TeacherDTO {

    private String email;
    private String name;
    private String pictureURL;
    private Integer price;
    private SubjectEntity subjectEntity;
    private List<CalendarDTO> calendarDTOS;

    public TeacherDTO(String email, String name, String pictureURL, Integer price, SubjectEntity subjectEntity, List<CalendarDTO> calendarDTOS) {

        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.price = price;
        this.subjectEntity = subjectEntity;
        this.calendarDTOS = calendarDTOS;
    }

    public static TeacherDTO of(String email, String name, String pictureURL, Integer price, SubjectEntity subjectEntity, List<CalendarDTO> calendarDTOS) {
        return new TeacherDTO(email, name, pictureURL, price, subjectEntity, calendarDTOS);
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public void setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

    public List<CalendarDTO> getCalendarDTOS() {
        return calendarDTOS;
    }

    public void setCalendarDTOS(List<CalendarDTO> calendarDTOS) {
        this.calendarDTOS = calendarDTOS;
    }
}
