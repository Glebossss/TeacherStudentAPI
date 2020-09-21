package com.example.demo.controller;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.exeption.AccessNotSuccessful;
import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import com.example.demo.service.UserService;
import com.example.demo.serviceImp.UserServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class HeadController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/about")
    public ResponseEntity<ResultDTO> about() {
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResultDTO> logout() {
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @GetMapping("/myprofile")
    @ApiOperation(value = "Return admin", response = AdminDTO.class)
    public UserDTO admininput(OAuth2AuthenticationToken auth) throws AccessNotSuccessful {

        final Map<String, Object> attrs = auth.getPrincipal().getAttributes();
        final String email = (String) attrs.get("email");
        final UserDTO userDTO = userServiceImp.findByLogin(email);
        return userDTO;
    }
}
