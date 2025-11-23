package umc.server.domain.mapping.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mapping.mapping.entity.MemberFood;

import java.util.List;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
    @Query("select mf from MemberFood mf " +
            " JOIN FETCH mf.member m " +
            " WHERE m.id=:memberId")
    List<MemberFood> findByMemberId(@Param("memberId") Long memberId);
}