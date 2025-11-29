package com.example.jerry.domain.service;

import com.example.jerry.domain.converter.MissionConverter;
import com.example.jerry.domain.dto.request.MissionCreateReqDto;
import com.example.jerry.domain.dto.request.MissionReqDto;
import com.example.jerry.domain.dto.response.MissionResDto;
import com.example.jerry.domain.entity.Mission;
import com.example.jerry.domain.entity.Restaurant;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.MissionErrorCode;
import com.example.jerry.domain.repository.MissionRepository;
import com.example.jerry.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    // 가게에 미션 추가
    public MissionResDto createMission(MissionCreateReqDto req) {

        // 1. 가게 존재 확인
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new TestException(MissionErrorCode.INVALID_RESTAURANT));

        // 2. LocalDate 변환
        LocalDate date = LocalDate.parse(req.getMissionDate());

        // 3. Mission 생성
        Mission mission = Mission.builder()
                .mission(req.getMission())
                .missionDate(date)
                .getPoints(req.getGetPoints())
                .restaurant(restaurant)
                .build();

        missionRepository.save(mission);

        return MissionResDto.from(mission);
    }

    public List<MissionResDto> getAvailableMissions(MissionReqDto req) {

        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());

        List<Mission> missions = missionRepository.findAvailableMissionsInRegion(
                req.getMemberId(),
                req.getRegion(),
                pageRequest
        );

        if (missions.isEmpty()) {
            throw new TestException(MissionErrorCode.REGION_NOT_FOUND);
        }

        return missions.stream()
                .map(MissionResDto::from)
                .toList();


    }
    public MissionResDto.MissionPageDto getMissionsByRestaurant(Integer restaurantId, Pageable pageable) {

        Page<Mission> page = missionRepository.findByRestaurant_RestaurantsId(restaurantId, pageable);

        return MissionConverter.toRestaurantMissionPage(page);
    }
}
