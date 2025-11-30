package umc.server.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}

