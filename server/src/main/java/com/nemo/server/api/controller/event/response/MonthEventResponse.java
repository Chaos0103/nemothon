package com.nemo.server.api.controller.event.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class MonthEventResponse {

    private Long eventId;
    private Long categoryId;
    private String title;
    private String start;
    private String end;
    private int goingDuration;

    @Builder
    public MonthEventResponse(Long eventId, Long categoryId, String title, LocalDateTime start, LocalDateTime end, int goingDuration) {
        this.eventId = eventId;
        this.categoryId = categoryId;
        this.title = title;
        this.start = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.end = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.goingDuration = goingDuration;
    }
}