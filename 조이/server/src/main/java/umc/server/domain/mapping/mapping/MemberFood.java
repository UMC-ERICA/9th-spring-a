package umc.server.domain.mapping.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="MemberFood")
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_id" )
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Food_id")
    private Food food;
}
