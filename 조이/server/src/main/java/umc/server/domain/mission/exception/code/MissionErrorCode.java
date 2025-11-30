package umc.server.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPaylaod.code.BaseErrorCode;


@AllArgsConstructor
@Getter
public enum MissionErrorCode implements BaseErrorCode {
FOUND(HttpStatus.NOT_FOUND,
        "MISSION404_1",
        "해당 미션을 찾지 못했습니다");
private HttpStatus status;
private String message;
private String code;

}
