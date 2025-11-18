package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantReqDto {

    private String name;
    private String openTime;     // "09:00"
    private String closeTime;    // "21:00"
    private Integer categoryId;  // Food 엔티티 ID
    private Integer addressId;   // Address 엔티티 ID
}
