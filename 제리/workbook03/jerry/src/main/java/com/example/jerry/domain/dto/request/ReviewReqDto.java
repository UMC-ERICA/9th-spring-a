package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewReqDto {

    private Long memberId;       // 리뷰 작성자
    private Integer restaurantId; // 어느 가게에 리뷰 작성하는지

    private String content;      // 리뷰 내용
    private Byte rating;         // 별점 (1~5)
    private String photo;        // 사진 URL (옵션)

}