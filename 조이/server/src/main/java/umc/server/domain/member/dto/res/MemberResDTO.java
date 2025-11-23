package umc.server.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MemberResDTO {
    @Builder
    @Getter
    public static class MemberRes{
        private Long memberId;
        private String name;
        private String email;
        private String tel;
        private Integer point;
    }
}
