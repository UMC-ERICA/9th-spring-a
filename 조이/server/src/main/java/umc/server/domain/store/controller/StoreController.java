package umc.server.domain.store.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.store.dto.req.StoreReqDTO;
import umc.server.domain.store.dto.res.StoreResDTO;
import umc.server.domain.store.exception.code.StoreSuccessCode;
import umc.server.domain.store.service.StoreService;
import umc.server.global.apiPaylaod.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    //가게등록
    @PostMapping("/register")
    public ApiResponse<StoreResDTO.StoreResponseDTO> registerStore(
            @RequestBody
            StoreReqDTO.StoreJoinReqDTO dto
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.FOUND, storeService.register(dto)
        );
    }}