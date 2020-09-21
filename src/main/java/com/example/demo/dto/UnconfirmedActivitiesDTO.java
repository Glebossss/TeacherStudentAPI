package com.example.demo.dto;

import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class UnconfirmedActivitiesDTO {
    private Long id;
    private String teacher;
    private String student;
    private Date dateStart;
    private Date dataEnd;
    private Long time;
    private Integer money;

    public UnconfirmedActivitiesDTO(String teacher, String student, Date dateStart, Date dataEnd, Long time, Integer money, Long id) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.dateStart = dateStart;
        this.dataEnd = dataEnd;
        this.time = time;
        this.money = money;
    }

    public static UnconfirmedActivitiesDTO of(String teacher, String student, Date dateStart, Date dataEnd, Long time, Integer money, Long id) {
        return new UnconfirmedActivitiesDTO(teacher, student, dateStart, dataEnd, time, money, id);
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
