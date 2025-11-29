package com.example.jerry.domain.dto.response;

import com.example.jerry.domain.entity.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestaurantResDto {

    private Integer restaurantId;
    private String name;
    private String openTime;
    private String closeTime;
    private String categoryName;
    private String address;

    public static RestaurantResDto from(Restaurant restaurant) {
        return RestaurantResDto.builder()
                .restaurantId(restaurant.getRestaurantsId())
                .name(restaurant.getName())
                .openTime(restaurant.getOpenTime() != null ? restaurant.getOpenTime().toString() : null)
                .closeTime(restaurant.getCloseTime() != null ? restaurant.getCloseTime().toString() : null)
                .categoryName(restaurant.getCategory().getCategory().name())
                .address(restaurant.getAddress().getAddress())
                .build();
    }
}