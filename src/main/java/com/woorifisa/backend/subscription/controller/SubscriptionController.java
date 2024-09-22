package com.woorifisa.backend.subscription.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.common.security.encryption.EncryptService;
import com.woorifisa.backend.common.security.springsecurity.MemberDetail;
import com.woorifisa.backend.subscription.dto.PaymentInsertDTO;
import com.woorifisa.backend.subscription.dto.PaymentPrintDTO;
import com.woorifisa.backend.subscription.service.SubscriptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/subscription")
@Tag(name = "subscription controller")
public class SubscriptionController {

    @Autowired
    public SubscriptionService SubscriptionService;

    @Autowired
    private EncryptService encryptService;

    // 결제 정보list 출력
    @GetMapping("/paymentAllByMember")
    @Operation(summary = "결제방식 출력 (개발완료)", description = "memNum값으로 결제list 출력")
    public List<PaymentPrintDTO> paymentAllByMember(@AuthenticationPrincipal MemberDetail memberDetail) {
        return SubscriptionService.paymentAllByMember(memberDetail.getMemNum());
    }

    // 결제 정보 추가
    @PostMapping("/paymentInsert")
    @Operation(summary = "결제방식 추가 및 토스 빌링키 발급 (수정 중)", description = "결제 방식 추가")
    public int insertCard(@RequestBody Map<String, Object> reqMap, @AuthenticationPrincipal MemberDetail memberDetail) {
        String memNum = memberDetail.getMemNum();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        Date memBirth_date = SubscriptionService.getMemBirth(memNum);

        if (memBirth_date instanceof java.sql.Date) { // memBirth_date가 java.sql.Date일 경우 java.util.Date로 변환
            memBirth_date = new java.util.Date(memBirth_date.getTime());
        }

        LocalDate memBirth_localDate = memBirth_date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        String memBirth = (memBirth_localDate).format(formatter);

        Map<String, String> map = SubscriptionService.getBillingKey((String) reqMap.get("payCard"),
                (String) reqMap.get("payExp"), memNum, memBirth);

        if (map == null) {
            return 0;
        }

        String encryptedBillingKey = encryptService.encryptBillingKey(map.get("billingKey")); // 암호화 적용된 빌링키

        PaymentInsertDTO dto = new PaymentInsertDTO();
        dto.setMemNum(memNum);
        // dto.setPayBillingKey(map.get("billingKey"));
        dto.setPayBillingKey(encryptedBillingKey);
        dto.setPayBrand(map.get("brand"));
        dto.setPayCard(map.get("card"));

        return SubscriptionService.insertCard(dto);
    }
  
    // 구독하기
    @PostMapping("/subscriptionInsert")
    @Operation(summary = "구독하기 추가 (개발 완료)", description = "로그인한 회원정보와 결제정보, 상품정보로 구독")
    public String subscriptionInsert(@RequestBody SubscriptionDTO dto, @AuthenticationPrincipal MemberDetail memberDetail) {
        String memNum = memberDetail.getMemNum();
        dto.setMemNum(memNum);

        // 조회수 log 남기기
        log.info(String.format("Subscription - %s - %s", dto.getProdNum(), memNum));

        return SubscriptionService.subscriptionInsert(dto);
    }
    
    // 매일 자정에 구독 테이블 update 및 자동 결제
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateSubscriptionStatus(){
        SubscriptionService.updateSubscriptionStatus();
    }
}
