package umc.server.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;
import umc.server.global.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "ReviewReply")
public class ReviewReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reviewreply",nullable = false)
    private String reviewReply;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_id")
    private Review review;

    //베이스엔티티로 가져오니까 날자 안 해도 되지 않으까용?!
}
