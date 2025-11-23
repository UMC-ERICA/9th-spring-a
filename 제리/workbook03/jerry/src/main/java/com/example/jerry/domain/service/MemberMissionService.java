package com.example.jerry.domain.service;


import com.example.jerry.domain.converter.MemberMissionConverter;
import com.example.jerry.domain.dto.response.MemberMissionResDto;
import com.example.jerry.domain.dto.request.MissionChallengeReqDto;
import com.example.jerry.domain.entity.Member;
import com.example.jerry.domain.entity.MemberMission;
import com.example.jerry.domain.entity.Mission;
import com.example.jerry.domain.exception.TestException;
import com.example.jerry.domain.exception.code.MemberMissionErrorCode;
import com.example.jerry.domain.repository.MemberMissionRepository;
import com.example.jerry.domain.repository.MemberRepository;
import com.example.jerry.domain.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    //  미션 도전하기
    public MemberMissionResDto challengeMission(MissionChallengeReqDto req) {

        // 회원 조회
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        // 미션 조회
        Mission mission = missionRepository.findById(req.getMissionId())
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        // 중복 도전 방지
        memberMissionRepository.findByMember_MemberIdAndMission_MissionId(req.getMemberId(), req.getMissionId())
                .ifPresent(mm -> {
                    throw new TestException(MemberMissionErrorCode.ALREADY_COMPLETED);
                });

        // 새 도전 생성
        MemberMission memberMission = new MemberMission(
                false,   // clear = false
                member,
                mission
        );

        memberMissionRepository.save(memberMission);

        return MemberMissionResDto.from(memberMission);
    }


    public MemberMissionResDto clearMission(Long memberId, Long missionId) {

        MemberMission mm = memberMissionRepository.findByMember_MemberIdAndMission_MissionId(memberId, missionId)
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (Boolean.TRUE.equals(mm.getClear())) {
            throw new TestException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }

        MemberMission updated = MemberMission.builder()
                .userMissionId(mm.getUserMissionId())
                .member(mm.getMember())
                .mission(mm.getMission())
                .clear(true)
                .build();

        memberMissionRepository.save(updated);

        return MemberMissionResDto.from(updated);
    }

    public MemberMissionResDto.MemberMissionPageDto getInProgressMissions(Long memberId, Pageable pageable) {

        Page<MemberMission> page =
                memberMissionRepository.findByMember_MemberIdAndClearFalse(memberId, pageable);

        return MemberMissionConverter.toInProgressPage(page);
    }
}