package com.example.demo.service;

import com.example.demo.dto.СonfirmedActivitiesDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface СonfirmedActivitiesService {

    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable);

    public List<СonfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable);

    public void save(String emailTeacher, String emailStudent, Date dateStart, Date dateEnd);

    public List<СonfirmedActivitiesDTO> findAllStudent(String email);

    public List<СonfirmedActivitiesDTO> findAllTeacher(String email);

    public void del(String emailTeacher, String emailStudent, Date dateStart, Date dateEnd);

    public long countForStudent(String email);

    public long countForTeacher(String email);

    public СonfirmedActivitiesDTO findById(Long id);
}
