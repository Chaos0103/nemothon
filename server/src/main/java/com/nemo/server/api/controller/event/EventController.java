package com.nemo.server.api.controller.event;

import com.nemo.server.api.ApiResponse;
import com.nemo.server.api.controller.event.request.AddEventRequest;
import com.nemo.server.api.controller.event.response.MonthEventResponse;
import com.nemo.server.api.service.event.EventService;
import com.nemo.server.api.service.event.dto.AddEventDto;
import com.nemo.server.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        AddEventDto dto = AddEventDto.toDto(request);

        Long eventId = eventService.addEvent(memberEmail, request.getCategoryId(), dto);

        return ApiResponse.ok(eventId);
    }

    @GetMapping("/{period}")
    public ApiResponse<List<MonthEventResponse>> getMonth(
            @PathVariable String period) {
        String memberEmail = SecurityUtil.getCurrentLoginId();

        String[] day = period.split("/");
        LocalDateTime start = LocalDateTime.parse(day[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end = LocalDateTime.parse(day[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        List<MonthEventResponse> list = eventService.searchMonth(memberEmail, start, end);
        return ApiResponse.ok(list);
    }
}
