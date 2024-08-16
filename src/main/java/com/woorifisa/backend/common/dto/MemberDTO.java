package com.woorifisa.backend.common.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class MemberDTO {
   private String memNum;
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
