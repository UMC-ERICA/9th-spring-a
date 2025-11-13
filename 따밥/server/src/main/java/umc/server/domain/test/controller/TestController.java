package umc.server.domain.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.test.converter.TestConverter;
import umc.server.domain.test.dto.res.TestResDTO;
import umc.server.domain.test.service.query.TestQueryService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {
    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception{
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(
                code,
                TestConverter.toTestingDTO("This is a Test")
        );
    }

    // 예외 상황
    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ){
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(code, TestConverter.toExceptionDTO("This is a Test"));
    }

    // 500 예외 발생 엔드포인트
    @GetMapping("/webhook")
    public ApiResponse<Void> webhook(){
        testQueryService.error();

        GeneralSuccessCode code = GeneralSuccessCode._OK;
        return ApiResponse.success(code, null);
    }
}