package umc.server.domain.member.entity;

import umc.server.domain.member.enums.Authority;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.LoginType;
import umc.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String nicknname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.OTHER;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "birthday", nullable = false)
    private LocalDate birth;

    @Column(name = "member_photo")
    @Lob
    private String photo;

    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Authority authority = Authority.ROLE_USER;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "login_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private LoginType loginType = LoginType.INAPP;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    public void deactivate(){
        this.inactiveDate = LocalDateTime.now();
    }

}
