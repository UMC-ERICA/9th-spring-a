package umc.server.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isCompleted = :isCompleted " +
            "AND mm.id < :cursor " +
            "ORDER BY mm.id DESC")
    List<MemberMission> findInProgressMissions(@Param("memberId") Long memberId, @Param("isCompleted") boolean isCompleted, @Param("cursor") Long cursor, Pageable pageable);

    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "JOIN StoreAddress sa ON sa.store.id = s.id " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isCompleted = true " +
            "AND (sa.addr1 LIKE %:region% OR sa.addr2 LIKE %:region% " +
            "     OR sa.addr3 LIKE %:region% OR sa.addr4 LIKE %:region%) " +
            "ORDER BY mm.id DESC")
    List<MemberMission> findCompletedMissionsInRegion(
            @Param("memberId") Long memberId,
            @Param("region") String region,
            Pageable pageable
    );

}
