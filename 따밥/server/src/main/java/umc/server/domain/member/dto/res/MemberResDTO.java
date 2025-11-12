package umc.server.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {
    @Getter
    @Builder
    public static class LoginDTO{
        private Long memberId;
        private String memberNickname;
    }
}
