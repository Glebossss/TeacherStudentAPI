package com.example.demo.service;

import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.exeption.EntetyWithDateStartAndDateEndNotCreate;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CalendarService {

    public List<CalendarDTO> findAllRecord(String email, Pageable pageable);

    public void save(Date startDate, Date endtDate, Long timeLessons, String email) throws ParseException, EntetyWithDateStartAndDateEndNotCreate;
}
