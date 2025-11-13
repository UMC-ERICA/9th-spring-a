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

    @Getter
    public static class HomeDTO{
        private String region;
        private Long cursor;
        private Integer size;

        public HomeDTO(String region, Long cursor, Integer size) {
            this.region = region;
            this.cursor = cursor == null ? 0L : cursor;
            this.size = size == null ? 10 : size;
        }
    }
}
