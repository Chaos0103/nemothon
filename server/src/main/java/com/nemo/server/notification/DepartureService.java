package com.nemo.server.notification;

import com.nemo.server.notification.dto.NotificationUserDto;
import com.nemo.server.notification.service.NotificationService;
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
        notificationUserDtos.add(new NotificationUserDto(1L, "카카오 모빌리티 본사", LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m"))));

        // 현재 시간
        String now = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        for (NotificationUserDto notificationUserDto : notificationUserDtos) {
            String  expectdDepartureTime = notificationUserDto.getExpectedDepartureTime();

//            -- debug
            System.out.println("now : " + now);
            System.out.println(expectdDepartureTime);

            // 출발 예정시간이 현재와 같다면 알림 보내기
            if (expectdDepartureTime.equals(now)) {
                System.out.println("hello");
                notificationService.notify(notificationUserDto.getUserId(), notificationUserDto.getArrivalName() + "로 출발✈️ 해주세요!!");
            }
        }
    }
}
