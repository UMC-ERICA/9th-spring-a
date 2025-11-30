package umc.server.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import umc.server.domain.member.enums.Gender;
import umc.server.global.annotation.ExistFoods;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    @Builder
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            @ExistFoods
            List<Long> preferCategory

    ){}
    @Builder
    public record LoginDTO(
            String memberId
    ){}
}
