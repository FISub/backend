package com.woorifisa.backend.member.dto;

import java.time.LocalDate;

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
public class JoinDTO {
    private String memId;
    private String memPw;
    private String memName;
    private String memEmail;
    private String memPhone;
    private String memSex;
    private LocalDate memBirth;
    private String memAddr;
    private int memType;
}
