package umc.server.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {
    @Builder
    @Getter
    public static class WriteReviewReqDTO {
        public String content;
        public String title;
        public Long memberId;
        public Float star;
    }
    
}
