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
public class ProductDTO {
    private String prodNum;
    private String prodName;
    private int prodPrice;
    private String prodIntro;
    private String prodImg;
    private int prodCat;
    private String memNum;    
}
