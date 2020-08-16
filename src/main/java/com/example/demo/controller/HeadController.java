package com.example.demo.controller;

import com.example.demo.dto.result.ResultDTO;
import com.example.demo.dto.result.SuccessResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HeadController {

    @GetMapping("/about")
    public ResponseEntity<ResultDTO> about() {
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResultDTO> logout() {
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

}
