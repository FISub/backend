package com.woorifisa.backend.product.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // 상품 등록 (Insert)
    @Transactional
    @Override
    public String insertProduct(ProductDTO productDTO) {
        try {
            productRepository.insertProduct(
                productDTO.getProdName(),
                productDTO.getProdPrice(),
                productDTO.getProdIntro(),
                productDTO.getProdImg(),
                productDTO.getMemNum()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "실패: " + e.getMessage();
        }
        return "성공";
    }

    // 상품 수정 (Update)
    @Transactional
    @Override
    public String updateProduct(String prodNum, ProductDTO productDTO) {
        try {
            productRepository.updateProduct(
                productDTO.getProdNum(),
                productDTO.getProdName(),
                productDTO.getProdPrice(),
                productDTO.getProdIntro(),
                productDTO.getProdImg(),
                productDTO.getMemNum()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "실패: " + e.getMessage();
        }
        return "성공";
    }
}
