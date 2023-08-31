package com.nemo.server.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationUserDto {

    Long userId; // 유저 토큰

    String arrivalName; // 도착지 이름

    String expectedDepartureTime; // 예상 출발시간

    @Builder
    public NotificationUserDto(Long userId, String arrivalName, String expectedDepartureTime) {
        this.userId = userId;
        this.arrivalName = arrivalName;
        this.expectedDepartureTime = expectedDepartureTime;
    }
}
