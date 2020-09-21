package com.example.demo.controller;


import com.example.demo.dao.model.UserEntity;
import com.example.demo.dto.PageCountDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "Api for user service")
public class UserController {

    private static final int COUNT = 3;
    private static final String ADMINROLE = "ROLE_ADMIN";

    @Autowired
    UserService userService;

    //output all users for admin
    @GetMapping
    @ApiOperation(value = "Return list user", response = UserDTO.class)
    public List<UserDTO> allUser(@RequestParam(required = false, defaultValue = "0", name = "page") Integer pageCount,
                                 OAuth2AuthenticationToken auth) throws AccessNotSuccessful {
        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(ADMINROLE)) {
            return userService.findAllUserPagebal(PageRequest.of(
                    pageCount,
                    COUNT,
                    Sort.Direction.DESC,
                    "id"));
        } else {
            throw new AccessNotSuccessful();
        }
    }

    //Change role for user.
    @PostMapping
    @ApiOperation(value = "Changing user role", response = UserDTO.class)
    public String setStatys(@RequestParam String email) throws AccessNotSuccessful {
        //final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final UserDTO userDTO = userService.findByLogin(email);
        final String roleUser = userService.findByLogin(email).getRole().toString();
        if (roleUser.equals(ADMINROLE)) {
            userService.update(userDTO.getEmail());
        } else {
            throw new AccessNotSuccessful();
        }

        return "success";
    }

    @GetMapping("count")
    public PageCountDTO count() {
        return PageCountDTO.of(userService.count(), COUNT);
    }
}
