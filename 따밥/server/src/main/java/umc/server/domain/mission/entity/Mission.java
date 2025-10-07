package umc.server.domain.mission.entity;

import umc.server.domain.mission.enums.PointType;
import umc.server.domain.store.entity.Store;
import umc.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Double point = 100D;

    @Column(name = "point_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PointType pointType = PointType.ABSOLUTE;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "owner_num", nullable = false)
    private String ownerNum;
}
