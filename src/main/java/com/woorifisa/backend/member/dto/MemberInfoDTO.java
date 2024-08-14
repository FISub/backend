package com.woorifisa.backend.member.dto;

import java.util.Date;
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
public class MemberInfoDTO {
    private String memId;
    private String memPw;
    private String memName;
    private String memEmail;
    private String memPhone;
    private String memSex;
    private Date memBirth;
    private String memAddr;
    private int memType;
}
