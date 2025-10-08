package com.example.jerry.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(name = "more_address", length = 200)
    private String moreAddress;



    // 생성자
    public Address(String address, String moreAddress, String field) {
        this.address = address;
        this.moreAddress = moreAddress;
    }

}