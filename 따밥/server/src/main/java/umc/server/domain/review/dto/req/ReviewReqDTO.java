package umc.server.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {
    @Builder
    @Getter
    public static class ReviewReq{
        private String description;
        private Long memberId;
        private String photo;
        private Double score;
    }
}
