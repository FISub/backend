package com.woorifisa.backend.main.dto;

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
public class ReviewPrintDTO {
    private String revNum;
    private String memNum;
    private int revStar;
    private String revCont;
    private String memName;
}
