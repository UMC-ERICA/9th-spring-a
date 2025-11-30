package umc.server.domain.member.service;

import umc.server.domain.member.dto.req.MemberReqDTO;
import umc.server.domain.member.dto.res.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;

import java.util.Optional;

public interface MemberService {
    Member findByUsername(Long memberId);
    MemberResDTO.JoinDTO join(MemberReqDTO.JoinDTO request);
    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO request);
    MemberResDTO.HomeTopDTO getHomeTop(Long memberId, String region, Long cursor, int size);
    void missionSuccess(Long memberId, Long missionId);
    MemberResDTO.MyPageDTO getMyPage(Long memberId);
    ReviewResDTO.ReviewPreViewListDTO getMyReviews(Long memberId, Integer page);
    MemberResDTO.ChallengeMissionDTO challengeMission(Long memberId, Long missionId);
    MissionResDTO.MissionPreviewList getMyMissions(Long memberId, Integer page);
}
