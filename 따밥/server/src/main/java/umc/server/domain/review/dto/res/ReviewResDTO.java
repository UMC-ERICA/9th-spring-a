package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Getter
    @Builder
    public static class ReviewInfo{
        private Long reviewId;
        private String storeName;
        private Double score;
        private String description;
        private LocalDateTime createdAt;
    }

    @Builder
    public record reviewScoreDTO(
            Long reviewId,
            Double Score
    ){}

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean iSFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreviewDTO(
            String ownerNickname,
            Double score,
            String body,
            LocalDate createdAt
    ){}
}
