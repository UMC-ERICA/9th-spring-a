package com.example.jerry.domain.dto.response;
import lombok.*;
public class TestResDto {

    @Builder
    @Getter
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}