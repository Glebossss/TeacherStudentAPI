package com.example.demo.dao.repository;

import com.example.demo.dao.model.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u where u.email = :email")
    UserEntity findByEmail(@Param("email") String email);

    List<UserEntity> findAllUserEntitiesBy(Pageable pageable);

    @Query
    boolean existsByEmail(String email);

}
