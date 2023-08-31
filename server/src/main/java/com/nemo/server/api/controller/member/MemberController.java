package com.nemo.server.api.controller.member;

import com.nemo.server.api.ApiResponse;
import com.nemo.server.api.controller.member.request.LoginRequest;
import com.nemo.server.api.service.member.MemberService;
import com.nemo.server.security.TokenInfo;
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

    @PostMapping("/login")
    public ApiResponse<TokenInfo> login(@RequestBody LoginRequest request) {
        log.debug("MemberController.login");
        TokenInfo tokenInfo = memberService.login(request.getEmail(), request.getPassword());
        log.debug("tokenInfo={}", tokenInfo);
        return ApiResponse.ok(tokenInfo);
    }
}
