package umc.server.domain.alarm.entity;

import umc.server.domain.alarm.enums.AlarmType;
import umc.server.domain.member.entity.Member;
import umc.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "alarm_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    @Column(name = "body", nullable = false)
    @Lob
    private String body;

    @Column(name = "is_read", nullable = false)
    @Builder.Default
    private boolean isRead = false;
}
