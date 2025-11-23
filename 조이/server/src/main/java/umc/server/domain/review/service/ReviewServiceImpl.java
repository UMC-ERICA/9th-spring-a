package umc.server.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createReview(Long storeId, ReviewReqDTO.ReviewDTO request){
        Member member;
            member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new IllegalArgumentException("멤버 읍슈"));
        ;
        Store store=storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("가게 읍슈"));

        Review review= Review.builder()
                .member(member)
                .store(store)
                .reviewContext(request.getContent())
                .star(request.getStar())
                .build();
        reviewRepository.save(review);

    }


}
