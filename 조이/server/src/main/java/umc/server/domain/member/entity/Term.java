package umc.server.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mapping.mapping.MemberTerm;
import umc.server.domain.member.enums.Context;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

@Table(name = "Term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "context")
    @Enumerated(EnumType.STRING)
    private Context context;

    @OneToMany(mappedBy = "Term")
    private List<MemberTerm> memberTerms= new ArrayList<>();
}
