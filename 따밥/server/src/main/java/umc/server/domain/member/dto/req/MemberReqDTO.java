package umc.server.domain.member.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import umc.server.domain.member.enums.Gender;
import umc.server.global.annotation.ExistsFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
        @NotBlank String name,
        @NotNull Gender gender,
        String email,
        @NotNull String phoneNumber,
        @NotNull LocalDate birthday,
        String memberPhoto,
        @ExistsFoods
        List<Long> preferFood,
        String addr1,
        String addr2,
        String addr3,
        String addr4
    ){}

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
