package umc.server.domain.mission.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당하는 미션을 찾지 못했습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
