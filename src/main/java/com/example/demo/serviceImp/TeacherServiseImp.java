package com.example.demo.serviceImp;

import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.repository.SubjectRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiseImp implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Transactional
    @Override
    public List<TeacherDTO> findAllTeacher() {

        final List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherEntities.forEach(x -> teacherDTOList.add(x.toTeacherDTO()));

        return teacherDTOList;
    }

    @Transactional
    @Override
    public void update(String name, int price, String email) throws IllegalStateException {

        final TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
        final SubjectEntity subjectEntity = subjectRepository.findSubjectEntitiesByName(name);
        teacherEntity.setPrice(price);
        teacherEntity.setSubjectEntity(subjectEntity);
    }

    @Transactional
    @Override
    public void save(TeacherDTO teacherDTO) {

        final TeacherEntity teacherEntity = TeacherEntity.fromTeacherDTO(teacherDTO);
        if (teacherRepository.existsByEmail(teacherEntity.getEmail()))
            return;

        teacherRepository.save(teacherEntity);
    }

    @Transactional
    @Override
    public TeacherDTO findOne(String email)  {

        final TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
        TeacherDTO teacherDTO = teacherEntity.toTeacherDTO();

        return teacherDTO;
    }

    @Transactional
    @Override
    public List<TeacherDTO> findAllTeacherByPage(Pageable pageable) {

        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherRepository.findTeacherEntitiesBy(pageable).forEach(x -> {
            if (x.getPrice() == null)
                x.setPrice(0);
            SubjectEntity subjectEntity = x.getSubjectEntity();
            TeacherDTO teacherDTO = x.toTeacherDTO();
            teacherDTO.setSubjectEntity(subjectEntity);
            teacherDTOList.add(teacherDTO);
        });

        return teacherDTOList;
    }

    @Transactional
    @Override
    public long count() {
        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        Long count = Long.valueOf(teacherEntities.size());
        return count;
    }
}
