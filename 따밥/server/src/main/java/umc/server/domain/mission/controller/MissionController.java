package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/register")
    public ApiResponse<MissionResDTO.RegisterDTO> registerMission(
            @RequestBody MissionReqDTO.RegisterDTO request
    ){
        return ApiResponse.success(
                GeneralSuccessCode._CREATED,
                missionService.register(request)
        );
    }
}
