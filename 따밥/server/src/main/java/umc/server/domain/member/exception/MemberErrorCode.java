package umc.server.domain.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;
@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
            "닉네임은 필수입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT,
            "MEMBER409_1",
            "이미 사용중인 이메일입니다."),
    DUPLICATE_PHONE_NUMBER(HttpStatus.CONFLICT,
            "MEMBER409_2",
            "이미 사용중인 전화번호입니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_2",
            "해당 회원에게 요청한 미션이 할당된 적 없습니다."),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST,
            "비밀번호가 잘못되었습니다.",
            "MEMBER400_3"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
