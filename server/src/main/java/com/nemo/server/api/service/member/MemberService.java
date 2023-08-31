package com.nemo.server.api.service.member;

import com.nemo.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //login
    public void login(String email, String password) {

    }
}
