package com.nemo.server.domain.notification;

import com.nemo.server.api.service.event.dto.TwentyFourHourDataDto;
import com.nemo.server.domain.event.repository.EventQueryRepository;
import com.nemo.server.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UpdatePathTimeService {

    @Value("${kakaomobility.api.url}")
    private String url;

    @Value("${kakaomobility.api.key}")
    private String key;

    private final EventQueryRepository eventQueryRepository;
    private final EventRepository eventRepository;

    /**
     * 현재 날짜로부터 24시간안에 있는 일정들 조회 및 달라져 있다면 정보 업데이트
     */
    @Scheduled(cron = "* 0/10 * * * ?")
    private void scheduleGoingTime() {
        LocalDateTime nowDate = LocalDateTime.now();
        List<TwentyFourHourDataDto> twentyFourEvent =
                eventQueryRepository.getTwentyFourEvent(nowDate);

        for (TwentyFourHourDataDto twentyFourHourDataDto : twentyFourEvent) {
            Long eventId = twentyFourHourDataDto.getEventId();
            int goingTime = twentyFourHourDataDto.getGoingTime();
            double departureLatitude = twentyFourHourDataDto.getDepartureLatitude();
            double departureLongitude = twentyFourHourDataDto.getDepartureLongitude();
            double arrivalLatitude = twentyFourHourDataDto.getArrivalLatitude();
            double arrivalLongitude = twentyFourHourDataDto.getArrivalLongitude();

            // 출발지와 도착지로 걸리는 시간 계산
            String origin =  departureLongitude + "," + departureLatitude;
            String destination =  arrivalLongitude + "," + arrivalLatitude;
            int calGoingTime = calGoingTime(origin, destination)/60; // 초 -> 분 단위로 환산

            // 걸리는 시간이 이전과 같지 않다면 정보 업데이트
            if (goingTime != calGoingTime) {
                eventRepository.findById(eventId).ifPresent((event) -> {
                    event.updateGoingTime(goingTime);
                });
            }
        }
    }

    /**
     * 카카오 길찾기 api를 호출해서 걸린는 시간 계산 후 업데이트
     * @param origin 출발지 경도, 위도
     * @param destination 도착지 경도, 위도
     * @return 걸리는 시간
     */
    private int calGoingTime(String origin, String destination) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("origin", origin)
                .queryParam("destination", destination );
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        // api를 호출해 Map 타입으로 전달 받음
        ResponseEntity<Map> resultMap = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Map.class);

        // body(실제 데이터 정보)에서 routes 에 해당하는 값들 ArrayList<Map> 형태로 받음
        ArrayList<Map> routesList = (ArrayList<Map>) resultMap.getBody().get("routes");
        LinkedHashMap lm = (LinkedHashMap) routesList.get(0).get("summary");

//        -- debug
        System.out.println(routesList.get(0).get("summary"));
        System.out.println(lm.get("duration"));

        return (int) lm.get("duration");
    }
}
