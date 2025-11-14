package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.Mission;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findById(Long missionId);
}