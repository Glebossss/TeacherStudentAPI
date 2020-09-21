package com.example.demo.controller;


import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.dto.TeacherDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Api for teacher service")
public class TeacherController {

    private static final int COUNT = 2;
    private static final String TEACHERROLE = "ROLE_TEACHER";
    private static final String STUDENTROLE = "ROLE_STUDENT";

    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @Autowired
    CalendarService calendarService;

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "Return teacher", response = TeacherDTO.class)
    public TeacherDTO teacher(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0") Integer pageCount) throws AccessNotSuccessful {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        String email = (String) attrs.get("email");
        String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(TEACHERROLE))
            return teacherService.findOne(email);
        else
            throw new AccessNotSuccessful();
    }

    @PutMapping("/{email}")
    @ApiOperation(value = "Update teacher", response = TeacherDTO.class)
    public ResponseEntity<ResultDTO> update(OAuth2AuthenticationToken auth, @PathVariable("email") String email, @RequestParam Integer price, @RequestParam String subName) throws AccessNotSuccessful, IllegalStateException {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emails = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emails).getRole().toString();
        if (roleUser.equals(TEACHERROLE)) {
            teacherService.update(subName, price, emails);
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping(value = "/allteacher")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Return list teacher", response = SubjectDTO.class)
    public List<TeacherDTO> allTeacher(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emails = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emails).getRole().toString();
        if (roleUser.equals(STUDENTROLE)) {
            return teacherService.findAllTeacherByPage(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping("count")
    public PageCountDTO count() {
        return PageCountDTO.of(teacherService.count(), COUNT);
    }
}
