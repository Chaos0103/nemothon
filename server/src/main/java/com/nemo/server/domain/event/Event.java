package com.nemo.server.domain.event;

import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

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

    @ManyToOne
    private Member member;

    @ManyToOne
    private Category category;
}
