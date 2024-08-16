package com.woorifisa.backend.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

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
                productDTO.getProdCat(),
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
                productDTO.getProdCat(),
                productDTO.getMemNum()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "실패: " + e.getMessage();
        }
        return "성공";
    }

    // 상품 조회
    @Override
    @Transactional
    public List<ProductDTO> productAll(String memNum) {
        List<Product> product = productRepository.productAllBiz(memNum);

        List<ProductDTO> dtoList = product.stream()
                .map(prod -> modelMapper.map(prod, ProductDTO.class))
                .collect(Collectors.toList());
      return dtoList;
    }
}
