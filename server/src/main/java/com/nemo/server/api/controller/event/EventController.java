package com.nemo.server.api.controller.event;

import com.nemo.server.api.ApiResponse;
import com.nemo.server.api.controller.event.request.AddEventRequest;
import com.nemo.server.api.controller.event.response.DailyEventResponse;
import com.nemo.server.api.controller.event.response.MonthEventResponse;
import com.nemo.server.api.service.event.EventService;
import com.nemo.server.api.service.event.dto.AddEventDto;
import com.nemo.server.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/event")
@Slf4j
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ApiResponse<Long> addEvent(
            @Valid @RequestBody AddEventRequest request
    ) {
        String memberEmail = SecurityUtil.getCurrentLoginId();
//        AddEventDto dto = AddEventDto.toDto(request);

//        Long eventId = eventService.addEvent(memberEmail, request.getCategoryId(), dto);
        Long eventId = null;
        return ApiResponse.ok(eventId);
    }

    @GetMapping("/month/{period}")
    public ApiResponse<List<MonthEventResponse>> getMonth(
            @PathVariable String period) {
        String memberEmail = SecurityUtil.getCurrentLoginId();

        String[] day = period.split("_");

        String[] date = day[0].split("-");
        LocalDateTime start = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])).atStartOfDay();
        date = day[1].split("-");
        LocalDateTime end = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])).atStartOfDay();

//        LocalDateTime start = LocalDateTime.parse(day[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime end = LocalDateTime.parse(day[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<MonthEventResponse> list = eventService.searchMonth(memberEmail, start, end);
        return ApiResponse.ok(list);
    }

    @GetMapping("/day/{day}")
    public ApiResponse<List<DailyEventResponse>> getDaily(
            @PathVariable String day) {
        log.debug("EventController#getDaily");
        String memberEmail = SecurityUtil.getCurrentLoginId();
        String[] date = day.split("-");
        LocalDateTime start = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])).atStartOfDay();
//        LocalDateTime start = LocalDateTime.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<DailyEventResponse> dailyEventResponses = eventService.searchDaily(memberEmail, start);

        return ApiResponse.ok(dailyEventResponses);
    }
}
