package com.example.jerry.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurants_id", nullable = false)
    private Integer restaurantsId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Food category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;


    // 생성자
    public Restaurant(String name, LocalTime openTime, LocalTime closeTime, Food category, Address address) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.category = category;
        this.address = address;
    }
}