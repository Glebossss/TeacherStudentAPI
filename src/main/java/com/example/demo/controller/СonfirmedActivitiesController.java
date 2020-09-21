package com.example.demo.controller;


import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.dto.СonfirmedActivitiesDTO;
import com.example.demo.service.UnconfirmedActivitiesService;
import com.example.demo.service.UserService;
import com.example.demo.service.СonfirmedActivitiesService;
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
@Api(value = "Api for confirmed activities service")
public class СonfirmedActivitiesController {

    private static final int COUNT = 1;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";

    @Autowired
    UnconfirmedActivitiesService unconfirmedActivitiesService;

    @Autowired
    UserService userService;

    @Autowired
    СonfirmedActivitiesService сonfirmedActivitiesService;

    @GetMapping("/confirmedactivitiesforstudent")
    @ApiOperation(value = "Return list confirmed activities for student", response = СonfirmedActivitiesDTO.class)
    public List<СonfirmedActivitiesDTO> forStudent(OAuth2AuthenticationToken auth,
                                                   @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount, @RequestParam(required = false, defaultValue = "0") Long not) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailStudent = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailStudent).getRole().toString();
        if (roleUser.equals(STUDENTROLE))
            return сonfirmedActivitiesService.findAllUnconfirmedActivitiesForStudent(emailStudent, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
        else {
            throw new AccessNotSuccessful();
        }
    }

    @DeleteMapping("/confirmedactivitiesforstudent")
    @ApiOperation(value = "cancellation of a lesson", response = СonfirmedActivitiesDTO.class)
    public ResponseEntity<ResultDTO> dell(OAuth2AuthenticationToken auth,
                                          @RequestParam Long ids) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailStudent = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailStudent).getRole().toString();
        final СonfirmedActivitiesDTO сonfirmedActivitiesDTO = сonfirmedActivitiesService.findById(ids);
        if (roleUser.equals(STUDENTROLE)) {
            сonfirmedActivitiesService.del(сonfirmedActivitiesDTO.getTeacherEntity(), сonfirmedActivitiesDTO.getStudentEntity(),
                    сonfirmedActivitiesDTO.getDateStart(), сonfirmedActivitiesDTO.getDataEnd());
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else {
            throw new AccessNotSuccessful();
        }
    }

    @GetMapping("/confirmedactivitiesforteacher")
    @ApiOperation(value = "Return list confirmed activities for teacher", response = СonfirmedActivitiesDTO.class)
    public List<СonfirmedActivitiesDTO> forTeacher(OAuth2AuthenticationToken auth,
                                                   @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailTeachers = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailTeachers).getRole().toString();
        if (roleUser.equals(TEACHERROLE)) {
            return сonfirmedActivitiesService.findAllUnconfirmedActivitiesForTeacher(emailTeachers, PageRequest.of(pageCount,
                    COUNT, Sort.Direction.DESC, "id"));
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping("/confirmedactivitiesforstudent/count")
    public PageCountDTO countForStudent(OAuth2AuthenticationToken auth) {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        return PageCountDTO.of(сonfirmedActivitiesService.countForStudent(email), COUNT);
    }

    @GetMapping("/confirmedactivitiesforteacher/count")
    public PageCountDTO countForTeacher(OAuth2AuthenticationToken auth) {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        return PageCountDTO.of(сonfirmedActivitiesService.countForTeacher(email), COUNT);
    }
}
