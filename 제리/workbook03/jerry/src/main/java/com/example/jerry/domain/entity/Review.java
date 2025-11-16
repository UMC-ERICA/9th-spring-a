package com.example.jerry.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private Byte rating;

    @Column(length = 255, nullable = false)
    private String photo;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurants_id", nullable = false)
    private Restaurant restaurant;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // 전체 생성자
    public Review(String content, Byte rating, String photo, Member member, Restaurant restaurant) {
        this.content = content;
        this.rating = rating;
        this.photo = photo;
        this.member = member;
        this.restaurant = restaurant;
    }
}