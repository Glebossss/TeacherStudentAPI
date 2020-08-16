package com.example.demo.serviceImp;

import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.repository.CalendarRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.exeption.EntetyWithDateStartAndDateEndNotCreate;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CalendarServiceImp implements CalendarService {

    private final static int LESSONFIFTEENMINUTE = 15;
    private final static int LESSONTHIRTYMINUTE = 30;
    private final static int LESSONONEHOUR = 60;


    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Transient
    @Override
    public List<CalendarDTO> findAllRecord(String email, Pageable pageable) {

        List<CalendarDTO> calendarDTOS = new ArrayList<>();
        final List<CalendarEntity> calendarEntities = calendarRepository.findByTeacherEntity_Email(email, pageable);
        calendarEntities.forEach(x -> calendarDTOS.add(x.toCalendarDTO()));

        return calendarDTOS;
    }

    @Transient
    @Override
    public void save(Date startData, Date endData, Long timeLessons, String email) throws ParseException, EntetyWithDateStartAndDateEndNotCreate {

        final TeacherEntity teacherEntity = teacherRepository.findByEmail(email);
        Integer money;
        long l = (endData.getTime() - startData.getTime()) / 1000 / 60;
        if (teacherEntity.getPrice() == null)
            teacherEntity.setPrice(0);
        if (timeLessons == l) {
            if (timeLessons == LESSONFIFTEENMINUTE)
                money = teacherEntity.getPrice() / 6 + (teacherEntity.getPrice() / LESSONFIFTEENMINUTE);
            else if (timeLessons == LESSONTHIRTYMINUTE)
                money = teacherEntity.getPrice() / 3 + (teacherEntity.getPrice() / LESSONTHIRTYMINUTE);
            else if (timeLessons == LESSONONEHOUR)
                money = teacherEntity.getPrice() + (teacherEntity.getPrice() / LESSONONEHOUR);
            else
                money = teacherEntity.getPrice();
            CalendarEntity calendarEntity = new CalendarEntity(startData, endData, timeLessons, money);
            //check interval
            if (calendarRepository.createOrNo(startData, endData, email) == null) {
                calendarEntity.setTeacherEntity(teacherEntity);
                calendarRepository.save(calendarEntity);
            } else
                throw new EntetyWithDateStartAndDateEndNotCreate();
        }
    }
}
