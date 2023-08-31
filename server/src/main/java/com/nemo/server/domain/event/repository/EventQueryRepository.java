package com.nemo.server.domain.event.repository;

import com.nemo.server.api.controller.event.response.MonthEventResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
}
