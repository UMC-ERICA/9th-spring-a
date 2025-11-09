package com.example.jerry.domain.converter;
import com.example.jerry.domain.dto.response.TestResDto;

import lombok.*;
@Getter
@Builder
@AllArgsConstructor

public class TestConverter {

    // 객체 -> DTO
    public static TestResDto.Testing toTestingDTO(
            String testing
    ) {
        return TestResDto.Testing.builder()
                .testString(testing)
                .build();
    }

    // 객체 -> DTO
    public static TestResDto.Exception toExceptionDTO(
            String testing
    ){
        return TestResDto.Exception.builder()
                .testString(testing)
                .build();
    }
}
