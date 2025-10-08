package com.example.jerry.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id", nullable = false)
    private Long missionId;

    @Column(length = 200, nullable = false)
    private String mission;

    @Column(name = "mission_date", nullable = false)
    private LocalDate missionDate;

    @Column(name = "get_points", nullable = false)
    private Integer getPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;

    // 생성자
    public Mission(String mission, LocalDate missionDate, Integer getPoints, Restaurant restaurant) {
        this.mission = mission;
        this.missionDate = missionDate;
        this.getPoints = getPoints;
        this.restaurant = restaurant;
    }
}
