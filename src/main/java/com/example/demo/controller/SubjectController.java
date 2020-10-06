package com.example.demo.controller;

import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.service.SubjectService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Api for subject service")
public class SubjectController {

    private static final int COUNT = 3;
    private static final String ADMINROLE = "ROLE_ADMIN";

    @Autowired
    SubjectService subjectService;

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "Return list subject", response = SubjectDTO.class)
    public List<SubjectDTO> allSubject(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0", value = "page") Integer pageCount) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(ADMINROLE))
            return subjectService.findAllSubjectByPageable(PageRequest.of(pageCount, COUNT, Sort.Direction.DESC, "id"));
        else
            throw new AccessNotSuccessful();
    }

    @PostMapping
    @ApiOperation(value = "Create new subject=", response = SubjectDTO.class)
    public ResponseEntity<ResultDTO> allSubject(OAuth2AuthenticationToken auth, @RequestBody SubjectDTO subjectDTO) throws IOException, AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(ADMINROLE)) {
            subjectService.save(subjectDTO);
            return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
        } else
            throw new AccessNotSuccessful();
    }

    @GetMapping("count")
    public PageCountDTO count() {
        return PageCountDTO.of(subjectService.count(), COUNT);
    }

    @GetMapping("all")
    public List<SubjectDTO> allSubject() {
        return subjectService.findAllSubject();
    }

}
