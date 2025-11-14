package umc.server.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    public record ReviewReq(
        String description,
        Long memberId,
        String photo,
        Double score
    ){}
}
