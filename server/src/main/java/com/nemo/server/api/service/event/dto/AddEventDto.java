package com.nemo.server.api.service.event.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddEventDto {

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime expectedDepartureTime;
    private int goingTime;
    private String arrivalName;
    private Double arrivalLatitude;
    private Double arrivalLongitude;
    private Double departureLatitude;
    private Double departureLongitude;

    @Builder
    public AddEventDto(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime expectedDepartureTime, int goingTime, String arrivalName, Double arrivalLatitude, Double arrivalLongitude, Double departureLatitude, Double departureLongitude) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedDepartureTime = expectedDepartureTime;
        this.goingTime = goingTime;
        this.arrivalName = arrivalName;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
    }
}
