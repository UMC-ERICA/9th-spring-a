package umc.server.domain.member.entity.mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class MemberTermId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "term_id")
    private Long termId;

}
