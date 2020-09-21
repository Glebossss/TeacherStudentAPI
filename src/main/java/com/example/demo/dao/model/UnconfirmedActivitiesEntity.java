package com.example.demo.dao.model;


import com.example.demo.dto.UnconfirmedActivitiesDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "unconfirmedactivities_table")
public class UnconfirmedActivitiesEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacherEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    private Long time;

    private Integer money;

    public UnconfirmedActivitiesEntity() {
    }

    public UnconfirmedActivitiesEntity(TeacherEntity teacherEntity, StudentEntity studentEntity, Date dateStart, Date dateEnd, Long time, Integer money) {
        this.teacherEntity = teacherEntity;
        this.studentEntity = studentEntity;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.time = time;
        this.money = money;
    }

    public static UnconfirmedActivitiesEntity of(TeacherEntity teacherEntity, StudentEntity studentEntity, Date dateStart, Date dataEnd, Long time, Integer money) {
        return new UnconfirmedActivitiesEntity(teacherEntity, studentEntity, dateStart, dataEnd, time, money);
    }

    public UnconfirmedActivitiesDTO toUnconfirmedActivitiesDTO() {
        return UnconfirmedActivitiesDTO.of(teacherEntity.getEmail(), studentEntity.getEmail(), dateStart, dateEnd, time, money, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dataEnd) {
        this.dateEnd = dataEnd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UnconfirmedActivitiesEntity{" +
                "id=" + id +
                ", teacherEntity=" + teacherEntity.getEmail() +
                ", studentEntity=" + studentEntity.getEmail() +
                '}';
    }
}
