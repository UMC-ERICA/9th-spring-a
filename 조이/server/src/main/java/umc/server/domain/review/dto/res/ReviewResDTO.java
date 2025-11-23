package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    @Getter
        public static class ReviewDTO {
        private Long storeName;
        private String memberName;
        private LocalDateTime createdAt;
        private String content;
        private String title;
        private Double star;
        }
}
