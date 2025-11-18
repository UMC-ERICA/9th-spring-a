package com.example.jerry.domain.controller;

import com.example.jerry.domain.dto.request.MissionReqDto;
import com.example.jerry.domain.dto.response.MissionResDto;
import com.example.jerry.domain.service.MissionService;
import com.example.jerry.global.apiPayload.ApiResponse;
import com.example.jerry.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/available")
    public ApiResponse<List<MissionResDto>> getAvailableMissions(
            @ModelAttribute MissionReqDto reqDto
    ) {
        List<MissionResDto> missions = missionService.getAvailableMissions(reqDto);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missions);
    }
}
