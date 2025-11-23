package umc.server.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPaylaod.code.GeneralErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ReviewResDTO.ReviewDTO  createReview(Long storeId, ReviewReqDTO.WriteReviewReqDTO dto){
        Member member;
            member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        ;
        Store store=storeRepository.findById(storeId).orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Review review;
        review = Review.builder()
                .member(member)
                .store(store)
                .reviewContext(dto.getContent())
                .star(dto.getStar())
                .build();
        reviewRepository.save(review);

        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .reviewContext(review.getReviewContext())
                .star(review.getStar())
                .build();

    }

}
