package com.nemo.server.domain.event;

import com.nemo.server.domain.category.Category;
import com.nemo.server.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public Event(Long id, String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime expectedDepartureTime, int goingTime, String arrivalName, Double arrivalLatitude, Double arrivalLongitude, Double departureLatitude, Double departureLongitude, Member member, Category category) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expectedDepartureTime = expectedDepartureTime;
        this.goingTime = goingTime;
        this.arrivalName = arrivalName;
        this.arrivalLatitude = arrivalLatitude;
        this.arrivalLongitude = arrivalLongitude;
        this.departureLatitude = departureLatitude;
        this.departureLongitude = departureLongitude;
        this.member = member;
        this.category = category;
    }

    public static Event toEntity(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime expectedDepartureTime, int goingTime, String arrivalName, Double arrivalLatitude, Double arrivalLongitude, Double departureLatitude, Double departureLongitude, Member member, Category category) {
        return Event.builder()
                .title(title)
                .startTime(startTime)
                .endTime(endTime)
                .expectedDepartureTime(expectedDepartureTime)
                .goingTime(goingTime)
                .arrivalName(arrivalName)
                .arrivalLatitude(arrivalLatitude)
                .arrivalLongitude(arrivalLongitude)
                .departureLatitude(departureLatitude)
                .departureLongitude(departureLongitude)
                .member(member)
                .category(category)
                .build();
    }

    public void updateGoingTime(int gonigTIme) {
        this.goingTime = goingTime;
    }
}
