package umc.server.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.domain.store.service.StoreService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreService storeService;

    @Override
    @Transactional
    public void createReview(Long storeId, ReviewReqDTO.ReviewReq request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .description(request.getDescription())
                .photo(request.getPhoto())
                .score(request.getScore())
                .build();

        reviewRepository.save(review);
        updateStoreScore(storeId);
    }

    private void updateStoreScore(Long storeId) {
        Double avgScore = reviewRepository.findAvgByStoreId(storeId);
        storeService.updateScore(storeId, avgScore);
    }
}
