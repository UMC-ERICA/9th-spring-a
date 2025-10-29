package umc.server.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 해당 회원의 MemberMission에 없는 미션만 조회한다. -> 도전 가능하다.
    // 도전 누르면, MemberMission에 포함된다. -> 즉, 진행 중인 상태가 된다.
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN StoreAddress sa ON sa.store.id = s.id " +
            "WHERE (sa.addr1 LIKE %:region% OR sa.addr2 LIKE %:region% " +
            "       OR sa.addr3 LIKE %:region% OR sa.addr4 LIKE %:region%) " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM MemberMission mm " +
            "    WHERE mm.mission.id = m.id " +
            "    AND mm.member.id = :memberId" +
            ") " +
            "ORDER BY m.deadline ASC")
    List<Mission> findAvailableMissionsInRegion(
            @Param("memberId") Long memberId,
            @Param("region") String region,
            Pageable pageable
    );
}
