package com.woorifisa.backend.main.service;

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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.common.repository.PaymentRepository;
import com.woorifisa.backend.common.repository.ProductRepository;
import com.woorifisa.backend.common.repository.ReviewRepository;
import com.woorifisa.backend.common.repository.SubscriptionRepository;
import com.woorifisa.backend.main.dto.PaymentInsertDTO;
import com.woorifisa.backend.main.dto.PaymentPrintDTO;
import com.woorifisa.backend.main.dto.ReviewPrintDTO;
import com.woorifisa.backend.main.exception.NoProductException;

import jakarta.transaction.Transactional;

@Service
public class MainServiceImpl implements MainService {

    @Value("${toss.api.secret-key}")
    private String apiSecretKey;

    @Value("${toss.api.billing-auth-url}")
    private String billingAuthUrl; // 빌링키 발급

    // private String billing; // 결제 승인

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ProductDTO> productPreview() {
        List<Product> product = productRepository.productPreview();

        List<ProductDTO> dtoList = product.stream()
                .map(prod -> modelMapper.map(prod, ProductDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @Transactional
    public List<ProductDTO> productAllByCategory(int category) {
        List<Product> product = null;
        if (category == -99999) {
            product = productRepository.findAll();
        } else {
            product = productRepository.productAllByCategory(category);
        }

        List<ProductDTO> dtoList = product.stream()
                .map(prod -> modelMapper.map(prod, ProductDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ProductDTO productDetail(String prodNum) throws NoProductException {
        Product product = productRepository.findById(prodNum).orElse(null);

        if (product != null) {
            return modelMapper.map(product, ProductDTO.class);
        } else {
            throw new NoProductException("존재하지 않는 상품입니다.");
        }
    }

    @Override
    public List<ReviewPrintDTO> reviewAllByProdNum(String prodNum) {
        List<Object[]> results = reviewRepository.reviewAllByProdNum(prodNum);
        List<ReviewPrintDTO> review = results.stream()
                .map(result -> new ReviewPrintDTO(
                        (String) result[0], // revNum
                        (String) result[1], // memNum
                        (Integer) result[2], // revStar
                        (String) result[3], // revCont
                        (String) result[4] // memName
                ))
                .collect(Collectors.toList());

        return review;
    }

    @Override
    @Transactional
    public ReviewPrintDTO reviewInsert(ReviewDTO dto) {
        int result = reviewRepository.reviewInsert(
                dto.getRevStar(),
                dto.getRevCont(),
                dto.getMemNum(),
                dto.getProdNum());

        if (result == 1) {
            Object[] object = reviewRepository.findTopByOrderByRevNumDesc().get(0);
            ReviewPrintDTO review = new ReviewPrintDTO(
                    (String) object[0], // revNum
                    (String) object[1], // memNum
                    ((Number) object[2]).intValue(), // revStar (Number to int)
                    (String) object[3], // revCont
                    (String) object[4] // memName
            );
            return review;
        }
        return null;
    }

    @Override
    public List<PaymentPrintDTO> paymentAllByMember(String memNum) {
        List<Object[]> payment = paymentRepository.paymentAllByMember(memNum);

        List<PaymentPrintDTO> dtoList = payment.stream()
                .map(result -> new PaymentPrintDTO(
                        (String) result[0], // payNum
                        (String) result[1],// payCard
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

        // 배송중으로 변경
        subscriptionRepository.updateToInDelivery(today); // 현재 날짜

        // 배송완료로 변경
        subscriptionRepository.updateToDeliveryCompleted(today, today.minusDays(2)); // 현재 날짜, 2일 전 날짜

        // 대기중으로 변경
        subscriptionRepository.updateToWaiting(today, today.minusDays(1)); // 현재 날짜, 1일 전 날짜
    }

    @Override
    @Transactional
    public String reviewDelete(Map<String, Object> reqMap) {
        int result = 0;

        if ((Integer) reqMap.get("memType") == 9) {
            result = reviewRepository.deleteByrevNum((String) reqMap.get("revNum"));
            return "review delete success";
        } else {
            result = reviewRepository.deleteByIdMemNum((String) reqMap.get("revNum"), (String) reqMap.get("memNum"),
                    (String) reqMap.get("prodNum"));

            if (result == 1) {
                return "review delete success";
            } else {
                return "review delete fail";
            }
        }
    }

    // Toss
    @Override
    public Date getMemBirth(String memNum) {
        return memberRepository.findMemBirthByMemNum(memNum);
    }

    @Override
    public Map<String, String> getBillingKey(String card, String exp, String memNum, String memBirth) {
        String expMonth = exp.split("/")[0];
        String expYear = exp.split("/")[1];

        // Basic 인증 헤더
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((apiSecretKey + ":").getBytes());

        // 요청 본문
        String reqBody = String.format(
                "{\"customerKey\":\"%s\",\"cardNumber\":\"%s\",\"cardExpirationYear\":\"%s\",\"cardExpirationMonth\":\"%s\",\"cardPassword\":\"12\",\"customerIdentityNumber\":\"%s\"}",
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