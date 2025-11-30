package umc.server.domain.mapping.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mapping.mapping.entity.MemberMission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("select mm from MemberMission mm "+
            "JOIN FETCH mm.member m "+
            "WHERE m.id =:memberId "+
            "AND mm.status = 'ON_GOING'")
    List<MemberMission> findByMemberIdWithFetch(@Param("memberId") Long memberId);

}