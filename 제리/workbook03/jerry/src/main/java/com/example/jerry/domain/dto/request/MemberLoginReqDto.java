package com.example.jerry.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberLoginReqDto {

    private String email;
    private String password;
}
