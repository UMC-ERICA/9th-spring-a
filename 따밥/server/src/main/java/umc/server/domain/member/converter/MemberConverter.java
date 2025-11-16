package umc.server.domain.member.converter;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberAddress;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberAddress toMemberAddress(Member member, MemberReqDTO.JoinDTO dto) {
        return MemberAddress.builder()
                .member(member)
                .addr1(dto.addr1())
                .addr2(dto.addr2())
                .addr3(dto.addr3())
                .addr4(dto.addr4())
                .build();
    }
    public static MemberResDTO.JoinDTO toJoinDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberReqDTO.JoinDTO dto){
        return Member.builder()
                .nickname(dto.name())
                .birth(dto.birthday())
                .photo(dto.memberPhoto())
                .phoneNumber(dto.phoneNumber())
                .gender(dto.gender())
                .email(dto.email())
                .build();
    }

    // Optional<Member> 객체 -> LoginDTO
    public static MemberResDTO.LoginDTO toLoginDTO(Member member){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .memberNickname(member.getNickname())
                .build();
    }

    public static MemberResDTO.HomeTopDTO toHomeTopDTO(
            Member member,
            String region,
            Integer totalMissionCount,
            List<MemberMission> missions,
            Long nextCursor,
            boolean hasNext
    ){
        return MemberResDTO.HomeTopDTO.builder()
                .point(member.getPoint())
                .region(region)
                .totalMissionCount(totalMissionCount)
                .missions(missions.stream()
                        .map(MemberConverter::toMissionInfo)
                        .collect(Collectors.toList()))
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
    public static MemberResDTO.MyReviewsDTO toMyReviewsDTO(Member member, List<Review> reviews){
        return MemberResDTO.MyReviewsDTO.builder()
                .reviews(reviews.stream()
                        .map(MemberConverter::toReviewInfo)
                        .collect(Collectors.toList()))
                .build();
    }

    private static ReviewResDTO.ReviewInfo toReviewInfo(Review review){
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getStoreName())
                .score(review.getScore())
                .description(review.getDescription())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static MemberResDTO.MyPageDTO toMyPageDTO(Member member){
        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname())
                .point(member.getPoint())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

    private static MissionResDTO.MissionInfo toMissionInfo(MemberMission mm){
        Mission m = mm.getMission();
        return MissionResDTO.MissionInfo.builder()
                .missionId(m.getId())
                .description(m.getDescription())
                .point(m.getPoint())
                .deadLine(m.getDeadline())
                .storeName(m.getStore().getStoreName())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }

    public static MemberResDTO.ChallengeMissionDTO toChallengeMissionDTO(MemberMission mm){
        return MemberResDTO.ChallengeMissionDTO.builder()
                .memberId(mm.getMember().getId())
                .missionId(mm.getMission().getId())
                .deadLine(mm.getMission().getDeadline())
                .build();
    }
}
