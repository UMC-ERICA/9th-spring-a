package umc.server.domain.member.converter;

import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;

import java.util.Optional;

public class MemberConverter {
    // Optional<Member> 객체 -> LoginDTO
    public static MemberResDTO.LoginDTO toLoginDTO(Optional<Member> member){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.get().getId())
                .memberNickname(member.get().getNickname())
                .build();
    }
}
