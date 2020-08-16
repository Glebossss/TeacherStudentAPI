package com.example.demo.serviceImp;

import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dao.repository.SubjectRepository;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImp implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Transient
    @Override
    public List<SubjectDTO> findAllSubject() {

        List<SubjectDTO> subjectDTO = new ArrayList<>();
        subjectRepository.findAll().forEach(x -> subjectDTO.add(x.toSubjectDTO()));

        return subjectDTO;
    }

    @Transient
    @Override
    public void save(SubjectDTO studentEntity) {

        SubjectEntity subjectEntity = SubjectEntity.fromDTO(studentEntity);
        subjectRepository.save(subjectEntity);
    }

    @Transient
    @Override
    public SubjectDTO findOne(long id) {
        return subjectRepository.findByID(id).toSubjectDTO();
    }

    @Transient
    @Override
    public List<SubjectDTO> findAllSubjectByPageable(Pageable pageable) {

        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        subjectRepository.findSubjectEntitiesBy(pageable).forEach(x -> subjectDTOS.add(x.toSubjectDTO()));

        return subjectDTOS;
    }

    @Transient
    @Override
    public void add(String email) {

        SubjectEntity newSubjectEntity = new SubjectEntity(email);
        subjectRepository.save(newSubjectEntity);
    }

    @Transient
    @Override
    public void saveFOrSttrat(SubjectEntity subjectEntity) {

        subjectRepository.save(subjectEntity);
    }
}
