package com.woorifisa.backend.main.service;

import java.util.Date;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.common.dto.SubscriptionDTO;
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
    public int insertCard(PaymentDTO dto);
    public String subscriptionInsert(SubscriptionDTO dto);
    public void updateSubscriptionStatus();
    public String reviewDelete(Map<String, Object> reqMap);

    // Toss
    public Date getMemBirth(String memNum);
    public String getBillingKey(String card, String exp, String memNum, String memBirth);
}
