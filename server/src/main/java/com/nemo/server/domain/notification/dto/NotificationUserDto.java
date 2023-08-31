package com.nemo.server.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationUserDto {

    Long userId; // 유저 토큰

    String arrivalName; // 도착지 이름

    String arrivalTime; // 도착 시간

    LocalDateTime expectedDepartureTime; // 예상 출발시간

    @Builder
    public NotificationUserDto(Long userId, String arrivalName, String arrivalTime, LocalDateTime expectedDepartureTime) {
        this.userId = userId;
        this.arrivalName = arrivalName;
        this.arrivalTime = arrivalTime;
        this.expectedDepartureTime = expectedDepartureTime;
    }
}
