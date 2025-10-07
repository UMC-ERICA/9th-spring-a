package umc.server.domain.member.entity;

import umc.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "customer_inquiry_response")
public class InquiryResponse extends BaseEntity {
    @Id
    @Column(name = "inquiry_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "body", nullable = false)
    @Lob
    private String body;
}
