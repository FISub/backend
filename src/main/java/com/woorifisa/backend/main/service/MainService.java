package com.woorifisa.backend.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;

@Service
public interface MainService {
    public String insertCard(PaymentDTO dto);
    public List<ProductDTO> preview();
    public List<ProductDTO> allProductByCategory(int category);
}
