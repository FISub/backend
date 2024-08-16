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
public class SubscriptionDTO {
    private String subNum;
    private int subPer;
    private LocalDate subStart;
    private LocalDate subDeli;
    private int subStat;
    private LocalDate subUpd;
    private int subCnt;
    private String memNum;
    private String prodNum;
    private String payNum;
}
