package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.mapping.mapping.MemberFood;
import umc.server.domain.member.enums.FoodType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Table(name = "Food")

public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foodtype")
    @Enumerated(EnumType.STRING)
    private FoodType foodtype;

    @OneToMany(mappedBy = "food") //member은 memberfood 엔티티 안의 필드이름
    private List<MemberFood> memberFoods= new ArrayList<>(); //arraylist로 초기화해서 널 값 아니고 빈 리스트로 존재 []


}