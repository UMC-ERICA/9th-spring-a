package com.example.jerry.domain.repository;

import java.util.Optional;
import com.example.jerry.domain.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행 중 (clear = false)
    Page<MemberMission> findByMember_MemberIdAndClearTrue(Long memberId, Pageable pageable);

    // 완료 (clear = true)
    Page<MemberMission> findByMember_MemberIdAndClearFalse(Long memberId, Pageable pageable);

    Optional<MemberMission> findByMember_MemberIdAndMission_MissionId(Long memberId, Long missionId);
}