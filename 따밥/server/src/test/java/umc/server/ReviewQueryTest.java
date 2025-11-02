package umc.server;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.review.service.ReviewQueryService;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.entity.StoreAddress;
import umc.server.domain.store.repository.StoreAddressRepository;
import umc.server.domain.store.repository.StoreRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReviewQueryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreAddressRepository storeAddressRepository;

    @Autowired
    private ReviewQueryService reviewQueryService;


    @BeforeEach
    void setUp() {
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

        Member m3 = memberRepository.save(
                Member.builder()
                        .nicknname("N3")
                        .gender(Gender.FEMALE)
                        .birth(LocalDate.of(2000, 1, 1))
                        .point(100)
                        .email("n3@test.com")
                        .phoneNumber("010-1111-2222")
                        .build());

        // Store 생성
        Store store1 = storeRepository.save(
                Store.builder()
                        .storeName("강남 맛집")
                        .openingTime(LocalTime.of(10, 0))
                        .closingTime(LocalTime.of(22, 0))
                        .build());

        Store store2 = storeRepository.save(
                Store.builder()
                        .storeName("홍대 카페")
                        .openingTime(LocalTime.of(9, 0))
                        .closingTime(LocalTime.of(23, 0))
                        .build());

        Store store3 = storeRepository.save(
                Store.builder()
                        .storeName("이태원 레스토랑")
                        .openingTime(LocalTime.of(11, 0))
                        .closingTime(LocalTime.of(23, 30))
                        .build());

        Store store4 = storeRepository.save(
                Store.builder()
                        .storeName("부산 해산물")
                        .openingTime(LocalTime.of(10, 0))
                        .closingTime(LocalTime.of(21, 0))
                        .build());

        // StoreAddress 생성
        storeAddressRepository.save(
                StoreAddress.builder()
                        .store(store1)
                        .addr1("서울")
                        .addr2("강남구")
                        .addr3("역삼동")
                        .addr4("123-45")
                        .latitude(37.4979)
                        .longtitude(127.0276)
                        .build());

        storeAddressRepository.save(
                StoreAddress.builder()
                        .store(store2)
                        .addr1("서울")
                        .addr2("마포구")
                        .addr3("홍대입구")
                        .addr4("67-89")
                        .latitude(37.5563)
                        .longtitude(126.9245)
                        .build());

        storeAddressRepository.save(
                StoreAddress.builder()
                        .store(store3)
                        .addr1("서울")
                        .addr2("용산구")
                        .addr3("이태원동")
                        .addr4("234-56")
                        .latitude(37.5347)
                        .longtitude(126.9945)
                        .build());

        storeAddressRepository.save(
                StoreAddress.builder()
                        .store(store4)
                        .addr1("부산")
                        .addr2("해운대구")
                        .addr3("우동")
                        .addr4("789-12")
                        .latitude(35.1587)
                        .longtitude(129.1604)
                        .build());

        // Review 생성 - 서울 강남 (4-5점대)
        reviewRepository.save(
                Review.builder()
                        .store(store1)
                        .member(m1)
                        .description("정말 맛있어요! 강남에서 최고입니다.")
                        .score(5.0)
                        .photo("photo1.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store1)
                        .member(m2)
                        .description("가격대비 훌륭합니다.")
                        .score(4.5)
                        .photo("photo2.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store1)
                        .member(m3)
                        .description("분위기도 좋고 음식도 맛있어요.")
                        .score(4.0)
                        .photo("photo3.jpg")
                        .build());

        // Review 생성 - 서울 홍대 (3-4점대)
        reviewRepository.save(
                Review.builder()
                        .store(store2)
                        .member(m1)
                        .description("커피가 맛있네요. 다시 올게요.")
                        .score(4.0)
                        .photo("photo4.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store2)
                        .member(m2)
                        .description("그냥 그래요. 보통입니다.")
                        .score(3.0)
                        .photo("photo5.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store2)
                        .member(m3)
                        .description("분위기는 좋은데 음식은 별로.")
                        .score(3.5)
                        .photo("photo6.jpg")
                        .build());

        // Review 생성 - 서울 이태원 (4-5점대)
        reviewRepository.save(
                Review.builder()
                        .store(store3)
                        .member(m1)
                        .description("이태원 최고 맛집!")
                        .score(5.0)
                        .photo("photo7.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store3)
                        .member(m2)
                        .description("외국 음식이 땡길 때 좋아요.")
                        .score(4.5)
                        .photo("photo8.jpg")
                        .build());

        // Review 생성 - 부산 (4.5-5점대)
        reviewRepository.save(
                Review.builder()
                        .store(store4)
                        .member(m1)
                        .description("부산 와서 꼭 가봐야 할 곳!")
                        .score(5.0)
                        .photo("photo9.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store4)
                        .member(m2)
                        .description("신선한 해산물 최고!")
                        .score(5.0)
                        .photo("photo10.jpg")
                        .build());

        reviewRepository.save(
                Review.builder()
                        .store(store4)
                        .member(m3)
                        .description("회가 정말 싱싱해요.")
                        .score(4.5)
                        .photo("photo11.jpg")
                        .build());
    }

    @Test
    @DisplayName("query type이 location 일 때")
    void queryTypeLocation() {
        List<Review> res = reviewQueryService.searchReview("서울", "location");
        assertThat(res).hasSize(8);

        for (Review review : res) {
            System.out.println(review);
        }

    }
}
