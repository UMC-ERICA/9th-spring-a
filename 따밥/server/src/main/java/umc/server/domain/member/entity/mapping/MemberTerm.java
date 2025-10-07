package umc.server.domain.member.entity.mapping;

import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_term")
public class MemberTerm {

    @EmbeddedId
    private MemberTermId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("termId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;
}
