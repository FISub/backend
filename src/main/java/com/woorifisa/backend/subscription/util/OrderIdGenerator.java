package com.woorifisa.backend.subscription.util;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {

    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "-_";
    private static final String ALL_ALLOWED = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;

    private static final Random RANDOM = new SecureRandom();

    // 랜덤으로 orderId를 생성하는 메서드 (길이도 무작위로 6~64 사이)
    public String generateOrderId() {
        int length = RANDOM.nextInt(59) + 6;  // 길이를 6에서 64 사이로 무작위로 결정
        StringBuilder orderId = new StringBuilder(length);

        // 첫 문자는 영문 대문자나 소문자로 시작 (무작위)
        orderId.append(UPPER_CASE.charAt(RANDOM.nextInt(UPPER_CASE.length())));

        for (int i = 1; i < length; i++) {
            orderId.append(ALL_ALLOWED.charAt(RANDOM.nextInt(ALL_ALLOWED.length())));
        }

        return orderId.toString();
    }
}
