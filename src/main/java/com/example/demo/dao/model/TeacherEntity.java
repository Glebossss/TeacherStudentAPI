package com.example.demo.dao.model;

import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.TeacherDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher_table")
public class TeacherEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String pictureURL;
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherEntity", fetch = FetchType.LAZY)
    private List<CalendarEntity> calendarEntities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;

    @OneToMany(mappedBy = "teacherEntity", cascade = CascadeType.ALL)
    private List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = new ArrayList<>();

    public TeacherEntity(String email, String name, String pictureURL) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public TeacherEntity(String email, String name, String pictureURL, SubjectEntity subjectEntity, List<CalendarEntity> calendarEntity) {
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
        this.subjectEntity = subjectEntity;
        this.calendarEntities = calendarEntity;
    }

    public TeacherEntity() {
    }

    public static TeacherEntity of(String email, String name, String pictureURL) {
        return new TeacherEntity(email, name, pictureURL);
    }

    public TeacherDTO toTeacherDTO() {

        return TeacherDTO.of(email, name, pictureURL, price, subjectEntity, calendarDTOS(calendarEntities));
    }

    public List<CalendarDTO> calendarDTOS(List<CalendarEntity> calendarEntities) {
        List<CalendarDTO> calendarDTO = new ArrayList<>();
        calendarEntities.forEach(x -> calendarDTO.add(x.toCalendarDTO()));

        return calendarDTO;
    }

    public static TeacherEntity fromTeacherDTO(TeacherDTO teacherDTO) {
        return of(teacherDTO.getEmail(), teacherDTO.getName(), teacherDTO.getPictureURL());
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

    public List<CalendarEntity> getCalendarEntities() {
        return calendarEntities;
    }

    public void setCalendarEntities(List<CalendarEntity> calendarEntities) {
        this.calendarEntities = calendarEntities;
    }

    public List<UnconfirmedActivitiesEntity> getUnconfirmedActivitiesEntities() {
        return unconfirmedActivitiesEntities;
    }

    public void setUnconfirmedActivitiesEntities(List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities) {
        this.unconfirmedActivitiesEntities = unconfirmedActivitiesEntities;
    }

    public void setCalendar(CalendarEntity calendar) {
        getCalendarEntities().add(calendar);
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", price=" + price +
                ", subjectEntity=" + subjectEntity +
                '}';
    }


}
