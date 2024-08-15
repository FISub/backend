package com.woorifisa.backend.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.main.dto.PaymentInsertDTO;
import com.woorifisa.backend.main.dto.PaymentPrintDTO;
import com.woorifisa.backend.main.dto.ReviewPrintDTO;
import com.woorifisa.backend.main.exception.NoProductException;

@Service
public interface MainService {
    public List<ProductDTO> productPreview();
    public List<ProductDTO> productAllByCategory(int category);
    public ProductDTO productDetail(String prodNum) throws NoProductException;
    public List<ReviewPrintDTO> reviewAllByProdNum(String prodNum);
    public ReviewPrintDTO reviewInsert(ReviewDTO dto);
    public List<PaymentPrintDTO> paymentAllByMember(String memNum);
    public String insertCard(PaymentInsertDTO dto);
}
