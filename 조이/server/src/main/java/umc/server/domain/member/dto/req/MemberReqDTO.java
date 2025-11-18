package umc.server.domain.member.dto.req;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class MemberReqDTO {
    @Builder
    @Getter
    public static class MemberReq{
        private Enum gender;
        private String name;
        private LocalDate birthday;
        private String address;

}}
