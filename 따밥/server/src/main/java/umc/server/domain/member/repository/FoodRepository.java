package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // Food 엔티티를 DB에 저장하고, 조회하기 위해서 Repository 만들기
}
