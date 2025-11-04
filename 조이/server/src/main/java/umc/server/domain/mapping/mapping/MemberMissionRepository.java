package umc.server.domain.mapping.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("select mm from MemberMission mm "+
            "JOIN Fetch mm.member m "+
            "WHERE m.id =:memberId "+
            "AND mm.status = 'ON_GOING'")
    List<MemberMission> findByMemberIdWithFetch(@Param("memberId") Long memberId);

}