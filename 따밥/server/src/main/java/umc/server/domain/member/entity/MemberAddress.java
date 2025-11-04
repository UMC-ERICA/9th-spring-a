package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_address")
public class MemberAddress {
    @Id
    @Column(name = "member_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "addr1", nullable = false)
    private String addr1;

    @Column(name = "addr2", nullable = false)
    private String addr2;

    @Column(name = "addr3", nullable = false)
    private String addr3;

    @Column(name = "addr4", nullable = false)
    private String addr4;

    public void updateAddress(String addr1, String addr2, String addr3, String addr4) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.addr4 = addr4;
    }
}
