package com.example.demo.configs;

import com.example.demo.dao.model.AdminEntity;
import com.example.demo.dao.model.StudentEntity;
import com.example.demo.dao.model.TeacherEntity;
import com.example.demo.dao.model.UserEntity;
import com.example.demo.dao.model.enums.RoleUser;
import com.example.demo.dao.repository.TeacherRepository;
import com.example.demo.email.EmailService;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class Handlers implements AuthenticationSuccessHandler, EmailService {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    AdminService adminService;

    private static final String ADMIN = "glebgomenyuk@gmail.com";
    private static final String EMAILTEACHERSECOND = "gomenyukgleb@gmail.com";

    //Method for sending message
    @Override
    public void sendMessageForRegistr(String email, String name) {
        SimpleMailMessage message = applicationContext.getBean(SimpleMailMessage.class);
        String text = String.format(message.getText(), name);
        message.setText(text);
        message.setTo(email);

        emailSender.send(message);
    }

    //First input in program
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = token.getPrincipal();
        Map<String, Object> atribute = user.getAttributes();
        if ((ADMIN).equals(atribute.get("email")) || (EMAILTEACHERSECOND).equals((String) atribute.get("email"))) {
            TeacherEntity teacherEntity = TeacherEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"));
            if (teacherRepository.existsByEmail((String) atribute.get("email")) == false) {
                sendMessageForRegistr((String) atribute.get("email"), (String) atribute.get("name"));
            }
            teacherService.save(teacherEntity.toTeacherDTO());
            if ((ADMIN).equals(atribute.get("email"))) {
                AdminEntity adminEntity = AdminEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.ADMIN);
                UserEntity userEntity = UserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.ADMIN);
                adminService.save(adminEntity);
                userService.save(userEntity);
                httpServletResponse.sendRedirect("/admin.html");
            } else if ((EMAILTEACHERSECOND).equals(atribute.get("email"))) {
                UserEntity allUser = UserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.TEACHER);
                userService.save(allUser);
                httpServletResponse.sendRedirect("/teacherprofile.html");
            }
        } else {
            StudentEntity studentEntity = StudentEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"));
            UserEntity allUser = UserEntity.of((String) atribute.get("email"), (String) atribute.get("name"), (String) atribute.get("picture"), RoleUser.STUDENT);
            String email = (String) atribute.get("email");
            if (studentService.findOne(email) == false)
                sendMessageForRegistr((String) atribute.get("email"), (String) atribute.get("name"));
            userService.save(allUser);
            studentService.save(studentEntity);
            httpServletResponse.sendRedirect("/students.html");
        }
    }
}
