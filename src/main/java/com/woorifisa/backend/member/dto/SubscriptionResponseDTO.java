package com.woorifisa.backend.member.dto;

import java.util.Date;

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
public class SubscriptionResponseDTO {
    private String subNum;
    private int subPer;
    private Date subStart; 
    private Date subDeli;
    private int subStat;
    private Date subUpd;
    private int subCnt;
    private String memNum;
    private String prodNum;
    private String payNum;
    private String imgURL;
}