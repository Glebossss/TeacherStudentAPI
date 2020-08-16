package com.example.demo.dto;


import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class СonfirmedActivitiesDTO {

    private String teacherEntity;
    private String studentEntity;
    private Date dateStart;
    private Date dataEnd;
    private Long time;
    private Integer money;

    public СonfirmedActivitiesDTO(String teacherEntity, String studentEntity, Date dateStart, Date dataEnd, Long time, Integer money) {
        this.teacherEntity = teacherEntity;
        this.studentEntity = studentEntity;
        this.dateStart = dateStart;
        this.dataEnd = dataEnd;
        this.time = time;
        this.money = money;
    }

    public static СonfirmedActivitiesDTO of(String teacherEntity, String studentEntity, Date dateStart, Date dataEnd, Long time, Integer money) {
        return new СonfirmedActivitiesDTO(teacherEntity, studentEntity, dateStart, dataEnd, time, money);
    }

    public String getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(String teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public String getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(String studentEntity) {
        this.studentEntity = studentEntity;
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
}
