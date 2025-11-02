package umc.server;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.server.domain.member.entity.Food;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.enums.FoodType;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.domain.member.repository.MemberFoodRepository;
import umc.server.domain.member.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class MemberFoodTest {
    @Autowired MemberRepository memberRepository;
    @Autowired FoodRepository foodRepository;
    @PersistenceContext private EntityManager em;
    @Autowired
    private MemberFoodRepository memberFoodRepository;

    @BeforeEach
    void setUp(){
        // Food
        Food f1 = foodRepository.save(Food.builder().foodName(FoodType.KOREAN).build());
        Food f2 = foodRepository.save(Food.builder().foodName(FoodType.JAPANESE).build());
        Food f3 = foodRepository.save(Food.builder().foodName(FoodType.NONE).build());

        // Members
        Member m1 = memberRepository.save(
                Member.builder()
                        .nicknname("N1")
                        .gender(Gender.FEMALE)
                        .birth(LocalDate.of(2003, 9, 4))
                        .point(100)
                        .email("n1@test.com")
                        .phoneNumber("010-1111-2222")
                        .build());

        Member m2 = memberRepository.save(
                Member.builder()
                        .nicknname("N2")
                        .gender(Gender.MALE)
                        .birth(LocalDate.of(2003, 3, 3))
                        .point(100)
                        .email("n2@test.com")
                        .phoneNumber("010-1111-2222")
                        .build());


        // MemberFood 매핑
        memberFoodRepository.save(MemberFood.builder().member(m1).food(f1).build());
        memberFoodRepository.save(MemberFood.builder().member(m1).food(f2).build());
        memberFoodRepository.save(MemberFood.builder().member(m1).food(f3).build());

        memberFoodRepository.save(MemberFood.builder().member(m2).food(f1).build());
        memberFoodRepository.save(MemberFood.builder().member(m2).food(f2).build());
        memberFoodRepository.save(MemberFood.builder().member(m2).food(f3).build());

        System.out.println("_______________________테스트 데이터 입력 완료___________________________");
        // 영속성 초기화해서 Lazy 동작/SQL 로그 확인 가능하게
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void testNPlusOne(){
        System.out.println("=== N+1: findAll() then access collections ===");
        List<Member> members = memberRepository.findAll();
        System.out.println("members.size() = " + members.size());

        for (Member m : members){
            System.out.println("Member: " + m.getNicknname());

        }
    }

}
