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
public class PaymentInsertDTO {
    private String payCard;
    private String payExp;
    private int payCvc;
    private int payPw;
    private String memNum;
}