package umc.server.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import umc.server.domain.member.entity.Member;
import umc.server.domain.store.entity.Store;
import umc.server.global.BaseEntity;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "Review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Float star;

    @Column(nullable = false)
    private String reviewContext;

    @Column(name = "date",nullable = false)
    private Date date; //베이스 엔티티로 수정날자 게시잘짜 가져오는데 이거 쓸 필요가 있을까요?

    // 양방향 조회를 원하면
    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private ReviewReply reviewReply;
    // 안 써도 리뷰에서 댓글을 조회하지 않는다면 필요 없음

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") // FK
    private Store store;



    //이미지 처리 안 함

}
