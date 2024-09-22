package com.woorifisa.backend.main.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.repository.ProductRepository;
import com.woorifisa.backend.common.repository.ReviewRepository;
import com.woorifisa.backend.main.dto.ReviewPrintDTO;
import com.woorifisa.backend.main.exception.NoProductException;

import jakarta.transaction.Transactional;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
  
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
}