package umc.server.domain.mission.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.res.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPaylaod.ApiResponse;
import umc.server.global.apiPaylaod.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService; // final 필수

    @GetMapping("/{memberId}/mission") // 조회 API니까 GET 사용
    public ApiResponse getMission(@PathVariable Long memberId) {
        MissionResDTO.MissionRes response = missionService.getMission(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,response);
    }
}

