package com.ll.exam.spring_restapi.app.member.dto.request;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;

    public boolean isNotValid() {
        return username == null || password == null || username.trim().length() == 0 | username.trim().length() == 0;
    }
}