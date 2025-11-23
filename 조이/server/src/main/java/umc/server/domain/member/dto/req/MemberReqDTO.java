package umc.server.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    @Builder
    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            List<Long> preferCategory

    ){}
    @Builder
    public record LoginDTO(
            String memderId
    ){}
}
