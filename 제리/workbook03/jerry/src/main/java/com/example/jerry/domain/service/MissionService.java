package com.example.jerry.domain.service;

import com.example.jerry.domain.dto.request.MissionReqDto;
import com.example.jerry.domain.dto.response.MissionResDto;
import com.example.jerry.domain.entity.Mission;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.MissionErrorCode;
import com.example.jerry.domain.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

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
}
