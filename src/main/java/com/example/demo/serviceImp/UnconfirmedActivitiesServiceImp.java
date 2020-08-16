package com.example.demo.serviceImp;

import com.example.demo.dao.model.CalendarEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.UnconfirmedActivitiesEntity;
import com.example.demo.dao.repository.CalendarRepository;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dao.repository.UnconfirmedActivitiesRepository;
import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.UnconfirmedActivitiesDTO;
import com.example.demo.service.UnconfirmedActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UnconfirmedActivitiesServiceImp implements UnconfirmedActivitiesService {

    @Autowired
    UnconfirmedActivitiesRepository unconfirmedActivitiesRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForStudent(String email, Pageable pageable) {

        final List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities = unconfirmedActivitiesRepository.findByStudentEntity_Email(email, pageable);
        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTO = new ArrayList<>();
        unconfirmedActivitiesEntities.forEach(x -> unconfirmedActivitiesDTO.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTO;
    }

    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllUnconfirmedActivitiesForTeacher(String email, Pageable pageable) {
        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTO = new ArrayList<>();
        unconfirmedActivitiesRepository.findByTeacherEntity_Email(email, pageable).
                forEach(x -> unconfirmedActivitiesDTO.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTO;
    }

    @Transient
    @Override
    public void save(String emailStudent, String emailTeacher, CalendarDTO calendarDTO) {

        final List<CalendarEntity> calendarEntities = calendarRepository.findCalendarEntityByCalenlfhDTO(calendarDTO.getDateStart(), calendarDTO.getDataEnd());
        CalendarEntity[] calendarEntity = new CalendarEntity[1];
        calendarEntities.forEach(x -> {
            if (x.getTeacherEntity().getEmail().equals(emailTeacher))
                calendarEntity[0] = x;
        });
        final StudentEntity studentEntity = studentRepository.findByStudentEmail(emailStudent);
        final TeacherEntity teacherEntity = calendarEntity[0].getTeacherEntity();
        final UnconfirmedActivitiesEntity unconfirmedActivitiesEntity =
                new UnconfirmedActivitiesEntity(teacherEntity, studentEntity,
                        calendarEntity[0].getDateStart(),
                        calendarEntity[0].getDateEnd(),
                        calendarEntity[0].getTime(),
                        calendarEntity[0].getMoney());
        final List<UnconfirmedActivitiesEntity> unconfirmedActivitiesEntities =
                unconfirmedActivitiesRepository.findByUser(unconfirmedActivitiesEntity.getDateStart(), unconfirmedActivitiesEntity.getDateStart(), emailStudent);
        if (unconfirmedActivitiesEntities.isEmpty()) {
            unconfirmedActivitiesRepository.save(unconfirmedActivitiesEntity);
            calendarRepository.delete(calendarEntity[0]);
            sendMessageForAdd(teacherEntity.getEmail(), teacherEntity.getName(), studentEntity.getName());
        } else
            throw new ArrayIndexOutOfBoundsException();
    }

    @Transient
    @Override
    public void declineActifities(String emailTeacher, String emailStudent, Date dateStart, Date dateEnd) {

        final UnconfirmedActivitiesEntity unconfirmedActivitiesEntity = unconfirmedActivitiesRepository.findByEmailTeacherAndStudent(emailTeacher, emailStudent, dateStart, dateEnd);
        unconfirmedActivitiesRepository.delete(unconfirmedActivitiesEntity);
        sendMessageForNot(unconfirmedActivitiesEntity.getStudentEntity().getEmail(),
                unconfirmedActivitiesEntity.getStudentEntity().getName(),
                unconfirmedActivitiesEntity.getTeacherEntity().getName());
    }

    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllStudent(String email) {

        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTOS = new ArrayList<>();
        unconfirmedActivitiesRepository.findByEmailStudent(email).forEach(x -> unconfirmedActivitiesDTOS.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTOS;
    }

    @Transient
    @Override
    public List<UnconfirmedActivitiesDTO> findAllTeacher(String email) {

        List<UnconfirmedActivitiesDTO> unconfirmedActivitiesDTOS = new ArrayList<>();
        unconfirmedActivitiesRepository.findByEmailTeacher(email).forEach(x -> unconfirmedActivitiesDTOS.add(x.toUnconfirmedActivitiesDTO()));

        return unconfirmedActivitiesDTOS;
    }

    private void sendMessageForAdd(String email, String nameTeacher, String nameStudent) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("New lesson");
        simpleMailMessage.setText("Hello:\n\n [%s] you have new lesson. Student [%s]");
        simpleMailMessage.setFrom(fromEmail);
        String text = String.format(simpleMailMessage.getText(), nameTeacher, nameStudent);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(email);

        emailSender.send(simpleMailMessage);
    }

    private void sendMessageForNot(String email, String nameStudent, String nameTeacher) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Lesson canceled");
        simpleMailMessage.setText("Hello:\n\n [%s] you were refused [%s]");
        simpleMailMessage.setFrom(fromEmail);
        String text = String.format(simpleMailMessage.getText(), nameTeacher, nameStudent);
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(email);

        emailSender.send(simpleMailMessage);
    }
}
