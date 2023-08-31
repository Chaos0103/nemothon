package com.nemo.server.api.controller.member.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
