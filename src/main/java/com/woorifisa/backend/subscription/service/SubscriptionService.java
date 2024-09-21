package com.woorifisa.backend.subscription.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.subscription.dto.PaymentInsertDTO;
import com.woorifisa.backend.subscription.dto.PaymentPrintDTO;

@Service
public interface SubscriptionService {
    public List<PaymentPrintDTO> paymentAllByMember(String memNum);
    public Date getMemBirth(String memNum);
    public Map<String, String> getBillingKey(String card, String exp, String memNum, String memBirth);
    public int insertCard(PaymentInsertDTO dto);
    public String subscriptionInsert(SubscriptionDTO dto);
    public void updateSubscriptionStatus();
}
