package com.nemo.server.api.service.event.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class TwentyFourHourDataDto {

    Long eventId; // 일정 식별키

    int goingTime; // 걸리는 시간

    double departureLatitude; // 출발지 위도

    double departureLongitude; // 출발지 경도

    double arrivalLatitude; // 도착지 위도

    double arrivalLongitude; // 도착지 경도

    @Builder
    public TwentyFourHourDataDto(Long eventId, int goingTime, double departureLatitude, double departureLongitude, double arrivalLatitude, double arrivalLongitude) {
        this.eventId = eventId;
        this.goingTime = goingTime;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
    }
}
