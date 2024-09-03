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
public class PaymentDTO {
    private String payNum;
    private String memNum;
    private String payCard;
    private String payExp;
    private int payCvc;
    private int payPw;
    private String payBillingKey;
}
