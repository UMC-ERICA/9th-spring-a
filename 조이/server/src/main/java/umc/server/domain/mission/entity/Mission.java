package umc.server.domain.mission.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mapping.mapping.MemberMission;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.entity.Store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="Mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "Mcontext", nullable = false)
    private String mcontext;

    @Column(name="Mlimit", nullable = false)
    private LocalDate mlimit;

    @Column(name = "finishscore", nullable = false)
    private Integer finishscore;

    @OneToMany(mappedBy = "Mission" )
    private List<MemberMission> membermission= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_id") // FK
    private Review review ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Store_id") // FK
    private Store store ;
}