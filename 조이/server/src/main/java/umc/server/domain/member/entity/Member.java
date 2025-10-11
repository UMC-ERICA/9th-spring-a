package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mapping.mapping.MemberFood;
import umc.server.domain.mapping.mapping.MemberMission;
import umc.server.domain.mapping.mapping.MemberTerm;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.review.entity.Review;
import umc.server.global.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@Getter

@Table(name = "Member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length=20, nullable=false)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable=false)
    private LocalDate birth;

    @Column(name = "address", nullable=false)
    private String address;

    @Column(name = "point",length=100)
    private Integer point;

    @Column(name = "email")
    private String email;

    @Column(name = "tel", length=11)
    private String tel;

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.REMOVE) //member은 memberfood 엔티티 안의 필드이름
    private List<MemberFood> memberFoods= new ArrayList<>();

    @OneToMany(mappedBy = "member" ,cascade = CascadeType.REMOVE) //member은 memberfood 엔티티 안의 필드이름
    private List<MemberTerm> memberTerms= new ArrayList<>();//arraylist로 초기화해서 널 값 아니고 빈 리스트로 존재 []

    @OneToMany(mappedBy = "member" )
    private List<MemberMission> membermission= new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();
}