package com.nemo.server.security;

import com.nemo.server.domain.member.Member;
import com.nemo.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByEmail(email);

        return findMember
            .map(this::createMemberUserDetails)
            .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    //해당하는 User의 데이터가 존재하면 UserDetails 객체롤 만들어서 반환
    private UserDetails createMemberUserDetails(Member member) {
        return User.builder()
            .username(member.getUsername())
            .password(passwordEncoder.encode(member.getPassword()))
            .roles(member.getRoles().toArray(new String[0]))
            .build();
    }

    private UserDetails createStoreUserDetails(Store store) {
        return User.builder()
            .username(store.getUsername())
            .password(passwordEncoder.encode(store.getPassword()))
            .roles(store.getRoles().toArray(new String[0]))
            .build();
    }
}
