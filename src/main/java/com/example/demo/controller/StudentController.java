package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Api(value = "Api for student service")
public class StudentController {

    private static final int COUNT = 3;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CalendarService calendarService;

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "Return studenta", response = StudentDTO.class)
    public StudentDTO student(OAuth2AuthenticationToken auth) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(STUDENTROLE))
            return studentService.findByEmail(email);
        else
            throw new AccessNotSuccessful();
    }

    @GetMapping(value = "/allstudent")
    @ApiOperation(value = "Return list studenta", response = StudentDTO.class)
    public List<StudentDTO> allStudent(OAuth2AuthenticationToken auth, @RequestParam(required = false, value = "page", defaultValue = "0") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(TEACHERROLE))
            return studentService.findAllStudentByPage(PageRequest.of(
                    pageCount,
                    COUNT,
                    Sort.Direction.DESC,
                    "id"));
        else
            throw new AccessNotSuccessful();
    }
}
