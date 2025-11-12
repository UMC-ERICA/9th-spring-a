package umc.server.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;
import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {
    @Getter
    @Builder
    public static class JoinDTO{
        private String name;
        private Gender gender;
        private String email;
        private String phoneNumber;
        private LocalDate birthday;
        private String memberPhoto;
    }
}
