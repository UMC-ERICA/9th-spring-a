package umc.server.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mapping.mapping.entity.MemberMission;
import umc.server.domain.mapping.mapping.repository.MemberMissionRepository;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPaylaod.code.GeneralErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public void challengeMission(Long memberId, Long storeId, Long missionId) {
        // 이미 도전 중인지 체크
        List<MemberMission> ongoingMissions = memberMissionRepository.findByMemberIdWithFetch(memberId);
        boolean alreadyOnGoing = ongoingMissions.stream()
                .anyMatch(mm -> mm.getStore().getId().equals(storeId) &&
                        mm.getMission().getId().equals(missionId));
        if (alreadyOnGoing) {
            throw new GeneralException(GeneralErrorCode.FORBIDDEN);
        }

        // Member, Store, Mission 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .store(store)
                .mission(mission)
                .status(Status.OnGoing)
                .build();

        memberMissionRepository.save(memberMission);
    }
}


