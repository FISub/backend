package com.woorifisa.backend.main.service;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;

@Service
public interface MainService {
    public String insertCard(PaymentDTO dto);
}
