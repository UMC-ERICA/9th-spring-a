package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.service.MissionService;
import umc.server.global.annotation.ProperPageSize;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/mission")
@Validated
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping("/store/register")
    public ApiResponse<MissionResDTO.RegisterDTO> registerMission(
            @RequestBody MissionReqDTO.RegisterDTO request
    ){
        return ApiResponse.success(
                GeneralSuccessCode._CREATED,
                missionService.register(request)
        );
    }

    @GetMapping("/store")
    public ApiResponse<MissionResDTO.MissionPreviewList> getStoreMissions(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "0") @ProperPageSize Integer page
    ){
        return ApiResponse.success(
                GeneralSuccessCode._OK,
                missionService.getStoreMissions(storeName, page)
        );
    }
}
