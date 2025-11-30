package umc.server.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.req.ReviewReqDTO;
import umc.server.domain.review.dto.res.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.repository.ReviewRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPaylaod.code.GeneralErrorCode;
import umc.server.global.apiPaylaod.exception.GeneralException;

import static umc.server.domain.store.entity.QStore.store;


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
    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND) );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO getStorebyReview(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);
        return ReviewConverter.toReviewPreviewListDTO(result);
    }


}

