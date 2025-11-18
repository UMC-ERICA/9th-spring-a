package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class MemberSignupReqDto {

    private String email;
    private String password;
    private String name;
    private String phone;

    private List<Integer> preferCategory;
}