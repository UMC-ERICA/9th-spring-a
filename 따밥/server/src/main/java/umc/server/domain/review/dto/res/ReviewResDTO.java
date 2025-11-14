package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
}
