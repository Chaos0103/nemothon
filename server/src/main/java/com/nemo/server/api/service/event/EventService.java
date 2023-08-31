package com.nemo.server.api.service.event;

import com.nemo.server.api.controller.event.response.DailyEventResponse;
import com.nemo.server.api.controller.event.response.MonthEventResponse;
import com.nemo.server.api.service.event.dto.AddEventDto;
import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.category.repository.CategoryRepository;
import com.nemo.server.domain.event.Event;
import com.nemo.server.domain.event.repository.EventQueryRepository;
import com.nemo.server.domain.event.repository.EventRepository;
import com.nemo.server.domain.member.Member;
import com.nemo.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final EventQueryRepository eventQueryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public Long addEvent(String memberEmail, Long categoryId, AddEventDto dto) {

        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(NoSuchElementException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(NoSuchElementException::new);

        Event event = Event.toEntity(dto.getTitle(), dto.getStartTime(), dto.getEndTime(), dto.getExpectedDepartureTime(), dto.getGoingTime(), dto.getArrivalName(), dto.getArrivalLatitude(), dto.getArrivalLongitude(), dto.getDepartureLatitude(), dto.getDepartureLongitude(), member, category);
        Event savedEvent = eventRepository.save(event);
        return savedEvent.getId();
    }

    public List<MonthEventResponse> searchMonth(String memberEmail, LocalDateTime startDay, LocalDateTime endDay) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(NoSuchElementException::new);
        return eventQueryRepository.getMonthEvent(member.getId(), startDay, endDay);
    }

    public List<DailyEventResponse> searchDaily(String memberEmail, LocalDateTime day) {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(NoSuchElementException::new);

        return eventQueryRepository.getDailyEvent(member.getId(), day);
    }
}
