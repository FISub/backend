package com.woorifisa.backend.product;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.woorifisa.backend.common.dto.ProductDTO;

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

}
