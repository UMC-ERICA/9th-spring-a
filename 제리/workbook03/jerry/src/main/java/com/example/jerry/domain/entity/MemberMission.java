package com.example.jerry.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id", nullable = false)
    private Integer userMissionId;

    @Column(nullable = false)
    private Boolean clear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;


    // 생성자
    public MemberMission(Boolean clear, User user, Mission mission) {
        this.clear = clear;
        this.user = user;
        this.mission = mission;
    }
}