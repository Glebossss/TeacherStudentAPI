package com.example.demo.dao.repository;

import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {

    List<CalendarEntity> findByTeacherEntity_Email(String email, Pageable pageable);

    List<CalendarEntity> findByTeacherEntity_Email(String email);

    @Query("SELECT u FROM CalendarEntity u where u.id = :id")
    CalendarEntity findByIds(@Param("id") Long id);


    @Query("SELECT u FROM CalendarEntity u where u.dateStart = :dateStart and u.dateEnd = :dataEnd and u.teacherEntity.email = :email")
    CalendarEntity createOrNo(@Param("dateStart") Date dateStart, @Param("dataEnd") Date dataEnd, @Param("email") String email);


    @Query("SELECT u FROM CalendarEntity u where u.dateStart = :dateStart and u.dateEnd = :dataEnd")
    List<CalendarEntity> findCalendarEntityByCalenlfhDTO(@Param("dateStart") Date dateStart, @Param("dataEnd") Date dataEnd);

}
