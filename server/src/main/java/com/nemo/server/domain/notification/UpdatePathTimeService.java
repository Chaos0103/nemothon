package com.nemo.server.domain.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UpdatePathTimeService {

    private final String url = "https://apis-navi.kakaomobility.com/v1/directions";
    private final String key = "a89dfda9888febb6a95066ca81427414";

    @Scheduled(cron = "* 0/1 * * * ?")
    public void updateTime() {
        String origin = "127.111202,37.394912";
        String destination =  "127.10824367964793,37.401937080111644";

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

        //  현재 날짜로부터 24시간안에 있는 일정들을 조회해서 길찾기 정보가 달라졌다면 업데이트


    }

    /**
     *  현재 날짜로부터 24시간안에 있는 일정들 조회
     * @param nowDate 현재 날짜
     */
    private void searchTime(LocalDateTime nowDate) {


    }
}
