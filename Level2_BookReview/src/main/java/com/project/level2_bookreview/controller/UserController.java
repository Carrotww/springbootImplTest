package com.project.level2_bookreview.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.level2_bookreview.dto.LoginDto;
import com.project.level2_bookreview.dto.SignUpRequestDto;
import com.project.level2_bookreview.dto.SignUpResponseDto;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/signup")
    public ResponseEntity sighUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        // Response 201 created
        SignUpResponseDto sighUpResponseDto = new SignUpResponseDto();
        // make service code

        return new ResponseEntity<>(sighUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDto loginDto) {
        // Response 200 ok
        // make service code
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
