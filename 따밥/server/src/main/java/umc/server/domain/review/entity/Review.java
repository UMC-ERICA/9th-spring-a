package umc.server.domain.review.entity;


import umc.server.domain.store.entity.Store;
import umc.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.entity.Member;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @Column(name = "score", nullable = false)
    @Builder.Default
    private Double score = 0D;

    @Column(name = "photo")
    @Lob
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id",  nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",   nullable = false)
    private Member member;
}
