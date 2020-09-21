package com.example.demo.dao.model;


import com.example.demo.dto.CalendarDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "calendar_table")
public class CalendarEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")

    private TeacherEntity teacherEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    private Long time;
    private Integer money;

    public CalendarEntity(Date date, Date dateEnd, Long time, Integer money) {
        this.dateStart = date;
        this.dateEnd = dateEnd;
        this.time = time;
        this.money = money;
    }

    public CalendarEntity() {
    }

    public static CalendarEntity of(Date dateStart, Date dataEnd, Long time, Integer money) {
        return new CalendarEntity(dateStart, dataEnd, time, money);
    }

    public CalendarDTO toCalendarDTO() {
        return CalendarDTO.of(id, dateStart, dateEnd, time, money);
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CalendarEntity{" +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", time=" + time +
                '}';
    }

}
