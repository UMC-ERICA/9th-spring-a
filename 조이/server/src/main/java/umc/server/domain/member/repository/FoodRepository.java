package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mapping.mapping.entity.MemberFood;

import java.util.List;

public interface FoodRepository extends JpaRepository<MemberFood, Long> {
    List<MemberFood> findByMemberId(@Param("memberId") Long memberId);
}
