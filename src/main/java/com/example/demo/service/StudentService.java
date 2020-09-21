package com.example.demo.service;

import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dto.StudentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    public List<StudentEntity> findAllStudents();

    public void save(StudentEntity studentEntity);

    public List<StudentDTO> findAllStudentByPage(Pageable pageable);

    public boolean findOne(String email);

    public StudentDTO findByEmail(String email);

    public long count();
}
