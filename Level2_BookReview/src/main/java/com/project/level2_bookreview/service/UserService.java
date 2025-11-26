package com.project.level2_bookreview.service;

import org.springframework.stereotype.Service;

import com.project.level2_bookreview.dto.LoginDto;
import com.project.level2_bookreview.dto.SignUpRequestDto;
import com.project.level2_bookreview.dto.SignUpResponseDto;
import com.project.level2_bookreview.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public SignUpResponseDto userSignUp(SignUpRequestDto signUpRequestDto) {
        // call repository

        return new SignUpResponseDto();
    }

    public void userLoginService(LoginDto loginDto) {

    }
}
