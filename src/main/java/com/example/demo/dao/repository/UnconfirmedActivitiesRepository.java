package com.example.demo.dao.repository;

import com.example.demo.dao.model.UnconfirmedActivitiesEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UnconfirmedActivitiesRepository extends JpaRepository<UnconfirmedActivitiesEntity, Long> {

    @Query("SELECT u FROM UnconfirmedActivitiesEntity u where u.studentEntity.email = :email")
    List<UnconfirmedActivitiesEntity> findByEmailStudent(@Param("email") String email);

    @Query("SELECT u FROM UnconfirmedActivitiesEntity u where u.teacherEntity.email = :email")
    List<UnconfirmedActivitiesEntity> findByEmailTeacher(@Param("email") String email);

    List<UnconfirmedActivitiesEntity> findByTeacherEntity_Email(String email, Pageable pageable);

    List<UnconfirmedActivitiesEntity> findByStudentEntity_Email(String email, Pageable pageable);

    @Query("SELECT u FROM UnconfirmedActivitiesEntity u where u.teacherEntity.email = :emailTeacher and u.studentEntity.email = :emailStudent and u.dateStart = :dataStart  and u.dateEnd = :dataEnd")
    UnconfirmedActivitiesEntity findByEmailTeacherAndStudent(@Param("emailTeacher") String emailTeacher, @Param("emailStudent") String emailStudent,
                                                             @Param("dataStart") Date dataStart, @Param("dataEnd") Date dataEnd);


    @Query("SELECT u FROM UnconfirmedActivitiesEntity u WHERE :from between u.dateStart and u.dateEnd and  (u.studentEntity.email = :email)  or :to between u.dateStart and u.dateEnd and  (u.studentEntity.email = :email)")
    List<UnconfirmedActivitiesEntity> findByUser(@Param("from") Date from,
                                                 @Param("to") Date to,
                                                 @Param("email") String email);

}
