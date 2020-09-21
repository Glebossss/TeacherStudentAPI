package com.example.demo.service;

import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dto.SubjectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    public List<SubjectDTO> findAllSubject();

    public void save(SubjectDTO subjectDTO);

    public SubjectDTO findOne(long id);

    public List<SubjectDTO> findAllSubjectByPageable(Pageable pageable);

    public void add(String email);

    public void saveFOrSttrat(SubjectEntity subjectEntity);

    public long count();

}
