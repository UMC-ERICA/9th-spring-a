package com.example.jerry.domain.service;

import com.example.jerry.domain.dto.request.RestaurantReqDto;
import com.example.jerry.domain.dto.response.RestaurantResDto;
import com.example.jerry.domain.entity.Address;
import com.example.jerry.domain.entity.Food;
import com.example.jerry.domain.entity.Restaurant;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.RestaurantErrorCode;
import com.example.jerry.domain.repository.AddressRepository;
import com.example.jerry.domain.repository.FoodRepository;
import com.example.jerry.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final AddressRepository addressRepository;

    public RestaurantResDto createRestaurant(RestaurantReqDto req) {

        // 1. Category 조회
        Food category = foodRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new TestException(RestaurantErrorCode.INVALID_CATEGORY));

        // 2. Address 조회
        Address address = addressRepository.findById(req.getAddressId())
                .orElseThrow(() -> new TestException(RestaurantErrorCode.INVALID_ADDRESS));

        // 3. Restaurant 생성
        Restaurant restaurant = Restaurant.builder()
                .name(req.getName())
                .openTime(req.getOpenTime() != null ? LocalTime.parse(req.getOpenTime()) : null)
                .closeTime(req.getCloseTime() != null ? LocalTime.parse(req.getCloseTime()) : null)
                .category(category)
                .address(address)
                .build();

        restaurantRepository.save(restaurant);

        return RestaurantResDto.from(restaurant);
    }

    public RestaurantResDto getRestaurant(Integer restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new TestException(RestaurantErrorCode.RESTAURANT_NOT_FOUND));

        return RestaurantResDto.from(restaurant);
    }
}
