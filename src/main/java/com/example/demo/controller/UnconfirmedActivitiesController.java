package com.example.demo.controller;

import com.example.demo.dto.CalendarDTO;
import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.UnconfirmedActivitiesDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.service.CalendarService;
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
@Api(value = "Api for unconfirmed activities service")
public class UnconfirmedActivitiesController {

    private static final int COUNT = 5;
    private static final String STUDENTROLE = "ROLE_STUDENT";
    private static final String TEACHERROLE = "ROLE_TEACHER";

    @Autowired
    UnconfirmedActivitiesService unconfirmedActivitiesService;

    @Autowired
    UserService userService;

    @Autowired
    CalendarService calendarService;


    @Autowired
    СonfirmedActivitiesService сonfirmedActivitiesService;

    @GetMapping("/unconfirmedactivitiesforstudent")
    @ApiOperation(value = "Return list unconfirmed activities for student", response = UnconfirmedActivitiesDTO.class)
    public List<UnconfirmedActivitiesDTO> forStudent(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(STUDENTROLE)) {
            return unconfirmedActivitiesService.findAllUnconfirmedActivitiesForStudent(email, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
        } else
            throw new AccessNotSuccessful();
    }

    @PostMapping("/unconfirmedactivitiesforstudent")
    @ApiOperation(value = "Enrollment of a student for a lesson", response = UnconfirmedActivitiesDTO.class)
    public ResponseEntity<ResultDTO> add(OAuth2AuthenticationToken auth, @RequestParam String email,
                                         @RequestParam Long ids) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emails = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emails).getRole().toString();
        if (roleUser.equals(STUDENTROLE)) {
            CalendarDTO calendarDTO = calendarService.findById(ids);
            unconfirmedActivitiesService.save(emails, email, calendarDTO);
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping("/unconfirmedactivitiesforteacher")
    @ApiOperation(value = "Return list unconfirmed activities for teacher", response = UnconfirmedActivitiesDTO.class)
    public List<UnconfirmedActivitiesDTO> forTeacher(OAuth2AuthenticationToken auth,
                                                     @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailTeachers = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailTeachers).getRole().toString();
        if (roleUser.equals(TEACHERROLE)) {
            return unconfirmedActivitiesService.findAllUnconfirmedActivitiesForTeacher(emailTeachers, PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
        } else
            throw new AccessNotSuccessful();
    }

    @DeleteMapping("/unconfirmedactivitiesforteacher")
    @ApiOperation(value = "Deviation of the task from the teacher", response = UnconfirmedActivitiesDTO.class)
    public ResponseEntity<ResultDTO> dell(OAuth2AuthenticationToken auth,
                                          @RequestParam Long ids) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailTeachers = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailTeachers).getRole().toString();
        final UnconfirmedActivitiesDTO unconfirmedActivitiesDTO = unconfirmedActivitiesService.findById(ids);
        if (roleUser.equals(TEACHERROLE)) {
            unconfirmedActivitiesService.declineActifities(unconfirmedActivitiesDTO.getTeacher(), unconfirmedActivitiesDTO.getStudent(),
                    unconfirmedActivitiesDTO.getDateStart(), unconfirmedActivitiesDTO.getDataEnd());
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else
            throw new AccessNotSuccessful();
    }

    @PostMapping("/unconfirmedactivitiesforteacher")
    @ApiOperation(value = "Сonfirmation of the task from the teacher", response = UnconfirmedActivitiesDTO.class)
    public ResponseEntity<ResultDTO> add(OAuth2AuthenticationToken auth, @RequestParam Long ids) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String emailTeachers = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(emailTeachers).getRole().toString();
        final UnconfirmedActivitiesDTO unconfirmedActivitiesDTO = unconfirmedActivitiesService.findById(ids);
        if (roleUser.equals(TEACHERROLE)) {
            сonfirmedActivitiesService.save(unconfirmedActivitiesDTO.getTeacher(), unconfirmedActivitiesDTO.getStudent(),
                    unconfirmedActivitiesDTO.getDateStart(), unconfirmedActivitiesDTO.getDataEnd());
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping("/unconfirmedactivitiesforstudent/count")
    public PageCountDTO countForStudent(OAuth2AuthenticationToken auth) {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        return PageCountDTO.of(unconfirmedActivitiesService.countForStudent(email), COUNT);
    }

    @GetMapping("/unconfirmedactivitiesforteacher/count")
    public PageCountDTO countForTeacher(OAuth2AuthenticationToken auth) {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        return PageCountDTO.of(unconfirmedActivitiesService.countForTeacher(email), COUNT);
    }
}
