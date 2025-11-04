package umc.server.domain.alarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.alarm.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm,Long> {
    // 읽지 않은 알람의 개수
    Integer countByMemberIdAndIsReadFalse(Long memberId);
}
