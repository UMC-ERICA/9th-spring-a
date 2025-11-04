package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.domain.mission.entity.Mission;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByMissionId(Long missionId);
}