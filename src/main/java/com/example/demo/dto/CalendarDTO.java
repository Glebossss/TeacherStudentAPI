package com.example.demo.dto;

import java.util.Date;

public class CalendarDTO {
    private Date dateStart;
    private Date dataEnd;
    private Long time;
    private Integer money;

    public CalendarDTO(Date dateStart, Date dataEnd, Long time, Integer money) {
        this.dateStart = dateStart;
        this.dataEnd = dataEnd;
        this.time = time;
        this.money = money;
    }

    public static CalendarDTO of(Date dateStart, Date dataEnd, Long time, Integer money) {
        return new CalendarDTO(dateStart, dataEnd, time, money);
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
