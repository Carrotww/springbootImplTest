package com.project.level2_bookreview.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    String email;
    String passwd;
    String nickName;
}
