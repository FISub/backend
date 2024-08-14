package com.woorifisa.backend.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.main.exception.NoProductException;

@Service
public interface MainService {
    public String insertCard(PaymentDTO dto);
    public List<ProductDTO> productPreview();
    public List<ProductDTO> productAllByCategory(int category);
    public ProductDTO productDetail(String prodNum) throws NoProductException;
}
