package com.project.level2_bookreview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.level2_bookreview.dto.LoginDto;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/signup")
    public void sighUp(@RequestBody Void t) {
        // Response 201 created
        return;
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody LoginDto loginDto) {
        // Response 200 ok
        return;
    }
}
