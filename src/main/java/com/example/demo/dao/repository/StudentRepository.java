package com.example.demo.dao.repository;

import com.example.demo.dao.model.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findStudentEntitiesBy(Pageable pageable);


    @Query("SELECT u FROM StudentEntity u where u.email = :email")
    StudentEntity findByStudentEmail(@Param("email") String email);

    @Query
    boolean existsByEmail(String email);

}
