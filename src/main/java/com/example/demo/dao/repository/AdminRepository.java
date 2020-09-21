package com.example.demo.dao.repository;

import com.example.demo.dao.model.AdminEntity;
import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    @Query
    boolean existsByEmail(String email);

    @Query("SELECT u FROM AdminEntity u where u.email = :email")
    AdminEntity findByEmail(@Param("email") String email);
}
