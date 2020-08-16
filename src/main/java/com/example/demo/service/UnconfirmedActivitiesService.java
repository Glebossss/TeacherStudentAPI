package com.example.demo.service;

import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.UnconfirmedActivitiesDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UnconfirmedActivitiesService {

    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable);

    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable);

    public List<UnconfirmedActivitiesDTO> findAllStudent(String email);

    public List<UnconfirmedActivitiesDTO> findAllTeacher(String email);

    public void save(String emailStudent, String emailTeacher, CalendarDTO calendarDTO);

    public void declineActifities(String emailTeacher, String emailStudent, Date dateStart, Date dateEnd);
}
