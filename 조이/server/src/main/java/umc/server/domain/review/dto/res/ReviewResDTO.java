package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    @Getter
        public static class ReviewDTO {
        private String storeName;
        private String memberName;
        private LocalDateTime createdAt;
        private String reviewContext;
        private String title;
        private Float star;
        private Long reviewId;

        }
}
