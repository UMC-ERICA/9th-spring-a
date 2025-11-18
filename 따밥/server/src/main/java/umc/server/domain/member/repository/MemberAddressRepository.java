package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.MemberAddress;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
}
