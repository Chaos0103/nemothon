package com.nemo.server.domain.notification;

import com.nemo.server.domain.notification.dto.NotificationUserDto;
import com.nemo.server.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartureService {

    private final NotificationService notificationService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void expectationScheduled() {
        // -- debug
        System.out.println("notification");

        // 출발 예정 시간 알림
        // 출발 예정 시간들의 정보를 가져옴
        List<NotificationUserDto> notificationUserDtos = new ArrayList<>();
        notificationUserDtos.add(new NotificationUserDto(
                1L, "카카오 모빌리티 본사",
                LocalDateTime
                        .now(ZoneId.of("Asia/Seoul"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime
                        .now(ZoneId.of("Asia/Seoul"))
                        .plusMinutes(5)));

        // 현재 시간
        String now = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // 출발 예정 시간보다 5분 일찍 알람 보냄
        for (NotificationUserDto notificationUserDto : notificationUserDtos) {
            String expectdDepartureTime = notificationUserDto.getExpectedDepartureTime()
                    .minusMinutes(5)
                    .format(DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm"));

//            -- debug
            System.out.println("now : " + now);
            System.out.println(expectdDepartureTime);

            // 출발 예정시간이 현재와 같다면 알림 보내기
            if (expectdDepartureTime.equals(now)) {
                System.out.println("hello");
                String arrivalOnlyTime = notificationUserDto.getArrivalTime().split(" ")[1];
                String arrivalHour = arrivalOnlyTime.split(":")[0];
                String arrivalMinute = arrivalOnlyTime.split(":")[1];
                notificationService.notify(notificationUserDto.getUserId(), "🌟 " + notificationUserDto.getArrivalName() + "로 지금 출발하셔야" +
                        " 도착시간인 " + arrivalHour + "시 " + arrivalMinute + "분"  + "에 맞게 도착하실 수 있어요! 😆");
            }
        }
    }
}
