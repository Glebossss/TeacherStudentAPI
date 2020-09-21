package com.example.demo.serviceImp;

import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Transactional
    @Override
    public List<StudentEntity> findAllStudents() {
        return studentRepository.findAll();
    }

    @Transient
    @Override
    public void save(StudentEntity studentEntity) {
        if (studentRepository.existsByEmail(studentEntity.getEmail()))
            return;

        studentRepository.save(studentEntity);
    }

    @Transient
    @Override
    public List<StudentDTO> findAllStudentByPage(Pageable pageable) {
        List<StudentDTO> studentDTO = new ArrayList<>();

        studentRepository.findStudentEntitiesBy(pageable).forEach(x -> studentDTO.add(x.toStudentDTO()));
        return studentDTO;
    }

    @Transactional
    @Override
    public boolean findOne(String email) {

        return studentRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public StudentDTO findByEmail(String email) {

        final StudentDTO studentDTO = studentRepository.findByStudentEmail(email).toStudentDTO();
        return studentDTO;
    }

    @Transactional
    @Override
    public long count() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        Long count = Long.valueOf(studentEntities.size());
        return count;
    }
}
