package com.example.demo.configs;

import com.example.demo.dao.model.SubjectEntity;
import com.example.demo.dao.repository.SubjectRepository;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.facade.AddSubjects;
import com.example.demo.facade.AddTeachers;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;

import java.beans.Transient;
import java.util.List;


@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    AddTeachers addTeachers;

    @Autowired
    TeacherRepository teacherRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    //First message for the user by the program
    @Bean
    @Scope("prototype")
    public SimpleMailMessage mailMessageRegistr() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome to the site");
        simpleMailMessage.setText("Hello:\n\n [%s]");
        simpleMailMessage.setFrom(fromEmail);

        return simpleMailMessage;
    }

    // Create Teachare and Subject After first starting programm
    @Transient
    @Bean
    public CommandLineRunner demo() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                List<SubjectEntity> subjectEntities = AddSubjects.addSubject();
                List<SubjectDTO> subjectEntitiesList = subjectService.findAllSubject();

                if (subjectEntitiesList.isEmpty()) {
                    addTeachers.teacherEntities();

                    subjectEntities.forEach(subjectService::saveFOrSttrat);
                }
            }
        };
    }
}
