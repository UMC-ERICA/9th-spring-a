package umc.server.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    @Getter
    public static class LoginDTO {
        private Long memberId;
        private String name;
        private String email;
        private String tel;
        private Integer point;
    }
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt
    ){}
}
