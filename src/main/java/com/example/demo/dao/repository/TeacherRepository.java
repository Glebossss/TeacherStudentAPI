package com.example.demo.dao.repository;

import com.example.demo.dao.model.TeacherEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    @Query("SELECT u FROM TeacherEntity u where u.email = :email")
    TeacherEntity findByEmail(@Param("email") String email);


    List<TeacherEntity> findTeacherEntitiesBy(Pageable pageable);

    @Query
    boolean existsByEmail(String email);
}
