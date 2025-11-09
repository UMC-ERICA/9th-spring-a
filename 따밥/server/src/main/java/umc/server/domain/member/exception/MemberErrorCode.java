package umc.server.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;
@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
            "닉네임은 필수입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
