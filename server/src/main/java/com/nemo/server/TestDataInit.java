package com.nemo.server;

import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.category.repository.CategoryRepository;
import com.nemo.server.domain.event.Event;
import com.nemo.server.domain.event.repository.EventRepository;
import com.nemo.server.domain.member.Member;
import com.nemo.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @PostConstruct
    public void init() throws Exception {
        Member member = member();
        Category category = category(member);
        event(member, category);
    }

    private Member member() {
        Member member = Member.builder()
            .email("kakao")
            .encryptedPwd("kakao")
            .name("카카오")
            .phoneNumber("010-1234-1234")
            .roles(Collections.singletonList("MEMBER"))
            .build();
        return memberRepository.save(member);
    }

    private Category category(Member member) {
        Category category = Category.builder()
            .name("해커톤")
            .colorCode("F4989A")
            .member(member)
            .build();
        return categoryRepository.save(category);
    }

    private void event(Member member, Category category) {
        Event e = Event.builder()
            .title("이벤트 1")
            .startTime(LocalDateTime.now())
            .endTime(LocalDateTime.now().plusHours(1))
            .expectedDepartureTime(LocalDateTime.now().minusHours(1))
            .goingTime(60)
            .arrivalName("카카오 모빌리티")
            .arrivalLatitude(1.1)
            .arrivalLongitude(1.1)
            .departureLatitude(1.1)
            .departureLongitude(1.1)
            .member(member)
            .category(category)
            .build();
        eventRepository.save(e);
    }
}
