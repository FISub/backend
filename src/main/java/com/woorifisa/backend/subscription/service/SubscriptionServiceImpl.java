package com.woorifisa.backend.subscription.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.common.repository.PaymentRepository;
import com.woorifisa.backend.common.repository.ProductRepository;
import com.woorifisa.backend.common.repository.SubscriptionRepository;
import com.woorifisa.backend.subscription.dto.PaymentInsertDTO;
import com.woorifisa.backend.subscription.dto.PaymentPrintDTO;
import com.woorifisa.backend.subscription.dto.ProductPayDTO;
import com.woorifisa.backend.subscription.util.OrderIdGenerator;

import jakarta.transaction.Transactional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Value("${toss.api.secret-key}")
    private String apiSecretKey;

    @Value("${toss.api.billing-auth-url}")
    private String billingAuthUrl;  // 빌링키 발급

    @Value("${toss.api.billing-pay-url}")
    private String billingPayUrl;   // 자동 결제

    @Autowired
    public PaymentRepository paymentRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderIdGenerator orderIdGenerator;
 
     // Basic 인증 헤더
    public String authHeader = "Basic " + Base64.getEncoder().encodeToString((apiSecretKey + ":").getBytes());

    @Override
    public List<PaymentPrintDTO> paymentAllByMember(String memNum) {
        List<Object[]> payment = paymentRepository.paymentAllByMember(memNum);

        List<PaymentPrintDTO> dtoList = payment.stream()
                .map(result -> new PaymentPrintDTO(
                        (String) result[0],  // payNum
                        (String) result[1],  // payCard
                        (String) result[2])) // payBrand
                .collect(Collectors.toList());
        return dtoList;
    }

        @Override
    @Transactional
    public int insertCard(PaymentInsertDTO dto) {
        int result = paymentRepository.insertCard(dto.getMemNum(), dto.getPayCard(), dto.getPayBillingKey(), dto.getPayBrand());
        return result == 1 ? 1 : 0;
    }

    @Override
    @Transactional
    public String subscriptionInsert(SubscriptionDTO dto) {
        int result = subscriptionRepository.subscriptionInsert(dto.getSubPer(),
                dto.getSubStart(),
                dto.getSubDeli(),
                dto.getSubStat(),
                dto.getSubUpd(),
                dto.getSubCnt(),
                dto.getMemNum(),
                dto.getProdNum(),
                dto.getPayNum());

        if (result == 1) {
            return "subscription insert success";
        }
        return "subscription insert fail";
    }

    @Override
    @Transactional
    public void updateSubscriptionStatus() {
        LocalDate today = LocalDate.now();

        // 결제 처리할 List 출력
        List<SubscriptionDTO> subscriptionList = subscriptionRepository.findByDeliveryDate(today);

        for(SubscriptionDTO subscription : subscriptionList){
            
            // 1. 자동 결제
            String paymentKey = autoPay(subscription);

            if(paymentKey != null){
                // 배송중으로 변경
                subscriptionRepository.updateToInDelivery(today, paymentKey); // 현재 날짜
            } else{
                subscriptionRepository.deleteSubById(subscription.getSubNum());
                System.out.println("결제 실패, 구독 취소");
            }
        }

        // 배송완료로 변경
        subscriptionRepository.updateToDeliveryCompleted(today, today.minusDays(2)); // 현재 날짜, 2일 전 날짜

        // 대기중으로 변경
        subscriptionRepository.updateToWaiting(today, today.minusDays(1)); // 현재 날짜, 1일 전 날짜
    }

    public String autoPay(SubscriptionDTO subscription){
        String billingKey = paymentRepository.findBillingKeyByPayNum(subscription.getPayNum());         // 빌링키(billingKey) 가져오기
        ProductPayDTO product = productRepository.findPayNameAndPayPrice(subscription.getProdNum());    // 상품명(orderName), 상품 가격(amount) 가져오기 
        String memNum = subscription.getMemNum();                                                       // customerKey
        String orderId = orderIdGenerator.generateOrderId();                                            // random한 영문 대소문자, 숫자, 특수문자 -, _, =, ., @ 를 최소 1개 이상 포함한 최소 2자 이상 최대 300자 이하의 문자열
        
        String reqBody = String.format(
                            "{\"billingKey\":\"%s\", \"amount\":\"%d\", \"customerKey\":\"%s\", \"orderId\":\"%s\", \"orderName\":\"%s\"}",
                            billingKey, product.getProdPrice(), memNum, orderId, product.getProdName());

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(billingPayUrl))
                    .header("Authorization", authHeader)
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(reqBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
            HttpResponse.BodyHandlers.ofString());

            JSONObject jsonRes = new JSONObject(response.body());
            
            if(jsonRes.has("paymentKey")){
                return jsonRes.getString("paymentKey");
            } else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Date getMemBirth(String memNum) {
        return memberRepository.findMemBirthByMemNum(memNum);
    }

    @Override
    public Map<String, String> getBillingKey(String card, String exp, String memNum, String memBirth) {
        String expMonth = exp.split("/")[0];
        String expYear = exp.split("/")[1];

        // 요청 본문
        String reqBody = String.format(
                "{\"customerKey\":\"%s\",\"cardNumber\":\"%s\",\"cardExpirationYear\":\"%s\",\"cardExpirationMonth\":\"%s\",\"customerIdentityNumber\":\"%s\"}",
                memNum, card, expYear, expMonth, memBirth);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(billingAuthUrl))
                    .header("Authorization", authHeader)
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(reqBody))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            JSONObject jsonRes = new JSONObject(response.body());

            if (jsonRes.has("billingKey")) {
                Map<String, String> map = new HashMap<String,String>();
                map.put("billingKey", jsonRes.getString("billingKey"));
                map.put("card", jsonRes.getString("cardNumber"));
                map.put("brand", jsonRes.getString("cardCompany"));
                return map;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}