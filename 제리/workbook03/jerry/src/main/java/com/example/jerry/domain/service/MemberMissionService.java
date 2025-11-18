package com.example.jerry.domain.service;


import com.example.jerry.domain.dto.response.MemberMissionResDto;
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

    // 미완료 미션 조회
    public Page<MemberMissionResDto> getUnclearedMissions(Long memberId, Pageable pageable) {
        Page<MemberMission> missions =
                memberMissionRepository.findByMemberIdAndClearFalse(memberId, pageable);

        return missions.map(MemberMissionResDto::from);
    }

    // 완료된 미션 조회
    public Page<MemberMissionResDto> getClearedMissions(Long memberId, Pageable pageable) {
        Page<MemberMission> missions =
                memberMissionRepository.findByMemberIdAndClearTrue(memberId, pageable);

        return missions.map(MemberMissionResDto::from);
    }

    // 특정 미션 완료 처리
    public MemberMissionResDto clearMission(Long memberId, Long missionId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        MemberMission mm = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new TestException(MemberMissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (mm.getClear()) {
            throw new TestException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }

        mm = MemberMission.builder()
                .userMissionId(mm.getUserMissionId())
                .member(member)
                .mission(mission)
                .clear(true)
                .build();

        memberMissionRepository.save(mm);

        return MemberMissionResDto.from(mm);
    }
}