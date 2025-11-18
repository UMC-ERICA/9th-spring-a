package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.domain.store.service.StoreService;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreService storeService;
    private final MemberService memberService;

    @Override
    @Transactional
    public ReviewResDTO.reviewScoreDTO createReview(Long storeId, ReviewReqDTO.ReviewReq request) {
        Member member = memberService.findByUsername(request.memberId());

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);

        reviewRepository.save(review);
        return ReviewConverter.toReviewResDTO(review.getId(),
                updateStoreScore(storeId)
        );
    }

    private Double updateStoreScore(Long storeId) {
        Double avgScore = reviewRepository.findAvgByStoreId(storeId);
        storeService.updateScore(storeId, avgScore);

        return avgScore;
    }
}
