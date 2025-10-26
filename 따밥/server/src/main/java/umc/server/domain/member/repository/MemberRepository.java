package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long memberId);

    // member 포인트 조회
    @Query("SELECT m.point FROM Member m WHERE m.id = :memberId")
    Integer findPointById(@Param("memberId") Long memberId);
}
