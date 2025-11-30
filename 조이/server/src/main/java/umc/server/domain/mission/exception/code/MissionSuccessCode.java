package umc.server.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPaylaod.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
          "STORE200_1",
                  "성공적으로 가게를 조회했습니다");
    private final HttpStatus status;
    private final String message;
    private final String code;
}
