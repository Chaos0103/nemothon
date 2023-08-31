package com.nemo.server.domain.event.repository;

import com.nemo.server.api.controller.event.response.DailyEventResponse;
import com.nemo.server.api.controller.event.response.MonthEventResponse;
import com.nemo.server.api.service.event.dto.TwentyFourHourDataDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.nemo.server.domain.category.QCategory.category;
import static com.nemo.server.domain.event.QEvent.event;
import static com.querydsl.core.types.Projections.constructor;

@Repository
public class EventQueryRepository {

    private final JPAQueryFactory queryFactory;

    public EventQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<MonthEventResponse> getMonthEvent(Long memberId, LocalDateTime startDay, LocalDateTime endDay) {
        return queryFactory.select(constructor(MonthEventResponse.class,
                        event.id,
                        event.category.id,
                        event.title,
                        event.startTime,
                        event.endTime,
                        event.goingTime
                ))
                .from(event)
                .join(event.category, category)
                .where(event.member.id.eq(memberId),
                        event.startTime.between(startDay, endDay))
                .fetch();
    }

    public List<DailyEventResponse> getDailyEvent(Long memberId, LocalDateTime day) {
        LocalDate startDay = day.toLocalDate();
        LocalDate endDay = startDay.plusDays(1);
        return queryFactory.select(constructor(DailyEventResponse.class,
                        event.id,
                        event.category.id,
                        event.title,
                        event.startTime,
                        event.endTime,
                        event.goingTime
                ))
                .from(event)
                .join(event.category, category)
                .where(event.member.id.eq(memberId),
                        event.startTime.between(startDay.atStartOfDay(), endDay.atStartOfDay()))
                .fetch();
    }

    /**
     * 들어오는 시간으로부터 24시간안에 해당하는 일정들의 출발지와 목적지를 가져온다.
     * @param startTime 24시간의 기준이 되는 시간
     * @return 일정 식별키, 걸린는 시간, 출발지 위도, 출발지 경도, 도착지 위도, 도착지 경도
     */
    public List<TwentyFourHourDataDto> getTwentyFourEvent(LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plusHours(24);

        return queryFactory.select(constructor(TwentyFourHourDataDto.class,
                        event.id,
                        event.goingTime,
                        event.departureLatitude,
                        event.departureLongitude,
                        event.arrivalLatitude,
                        event.arrivalLongitude
                ))
                .from(event)
                .where(event.startTime.between(startTime, endTime))
                .fetch()
                ;
    }
}
