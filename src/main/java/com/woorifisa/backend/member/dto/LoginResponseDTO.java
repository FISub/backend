package com.woorifisa.backend.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class LoginResponseDTO {
    private String memNum;
    private String memId;
    private int memType;
}
