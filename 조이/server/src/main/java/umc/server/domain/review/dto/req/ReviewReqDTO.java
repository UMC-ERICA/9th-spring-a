package umc.server.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {
    @Builder
    @Getter
    public static class ReviewDTO {
        private String content;
        private String title;
        private Long memberId;
        private Double star;
    }
}
