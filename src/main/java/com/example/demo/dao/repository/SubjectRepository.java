package com.example.demo.dao.repository;

import com.example.demo.dao.model.SubjectEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    @Query("SELECT u FROM SubjectEntity u where u.id = :id")
    SubjectEntity findByID(@Param("id") long id);

    List<SubjectEntity> findSubjectEntitiesBy(Pageable pageable);

    SubjectEntity findSubjectEntitiesByName(String name);


}
