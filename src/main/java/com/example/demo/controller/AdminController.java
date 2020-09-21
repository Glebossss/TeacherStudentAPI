package com.example.demo.controller;


import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.service.SubjectService;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Api for admin service")
public class AdminController {

    private static final String ADMINROLE = "ROLE_ADMIN";

    @Autowired
    UserService userService;

    //Controller for admin.
    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Return admin", response = AdminDTO.class)
    public UserDTO admininput(OAuth2AuthenticationToken auth) throws AccessNotSuccessful {

        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final UserDTO userDTO = userService.findByLogin(email);
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(ADMINROLE))
            return userDTO;
        else
            throw new AccessNotSuccessful();
    }
}
