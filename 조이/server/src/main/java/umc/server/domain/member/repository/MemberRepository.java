package umc.server.domain.member.repository;


import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(@Param("name") String name);

}