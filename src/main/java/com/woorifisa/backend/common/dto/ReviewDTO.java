package com.woorifisa.backend.common.dto;

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
public class ReviewDTO {
    private String revNum;
    private int revStar;
    private String revCont;
    private String memNum;
    private String prodNum;
}
