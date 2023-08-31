package com.nemo.server.api.controller.member;

import com.nemo.server.api.controller.member.request.LoginRequest;
import com.nemo.server.api.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //login member
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request) {

    }
}
