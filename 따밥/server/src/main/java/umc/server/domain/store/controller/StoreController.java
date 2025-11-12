package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    // 매장 등록
    @PostMapping("/register")
    public ApiResponse<Void> register(
            @RequestBody StoreReqDTO.RegisterDTO request
    ){
        storeService.register(request);
        GeneralSuccessCode code = GeneralSuccessCode._CREATED;
        return ApiResponse.success(code, null);
    }
}
