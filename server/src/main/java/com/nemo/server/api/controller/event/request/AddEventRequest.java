package com.nemo.server.api.controller.event.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AddEventRequest {

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int goingTime;
    private String arrivalName;
    private Double arrivalLatitude;
    private Double arrivalLongitude;
    private Double departureLatitude;
    private Double departureLongitude;
    private Long categoryId;

    @Builder
    private AddEventRequest(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime expectedDepartureTime, int goingTime, String arrivalName, Double arrivalLatitude, Double arrivalLongitude, Double departureLatitude, Double departureLongitude, Long categoryId) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.goingTime = goingTime;
        this.arrivalName = arrivalName;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
        this.categoryId = categoryId;
    }
}
