package umc.server.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;
import umc.server.domain.mission.dto.MissionResDTO;

import java.util.List;

public class MemberResDTO {
    @Getter
    @Builder
    public static class LoginDTO{
        private Long memberId;
        private String memberNickname;
    }

    @Getter
    @Builder
    public static class HomeTopDTO{
        private Integer point;
        private String region;
        private Integer totalMissionCount;

        private List<MissionResDTO.MissionInfo> missions;

        private Long nextCursor;
        private Boolean hasNext;
    }

    @Getter
    @Builder
    public static class MyPageDTO{
        private String nickname;
        private Integer point;
        private String email;
        private String phoneNumber;
    }
}
