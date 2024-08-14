package com.woorifisa.backend.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.repository.PaymentRepository;
import com.woorifisa.backend.common.repository.ProductRepository;
import com.woorifisa.backend.main.exception.NoProductException;

import jakarta.transaction.Transactional;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public String insertCard(PaymentDTO dto) {
        int result = paymentRepository.insertCard(dto.getMemNum(), dto.getPayCard(), dto.getPayExp(),
                dto.getPayCvc(), dto.getPayPw());

        if(result == 1){
            return "payment insert success";
        }
        return "payment insert fail";
    }

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
        if(category == -99999){
            product = productRepository.findAll();
        }else{
            product = productRepository.productAllByCategory(category);
        }        

        List<ProductDTO> dtoList = product.stream()
                                          .map(prod -> modelMapper.map(prod, ProductDTO.class))
                                          .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ProductDTO productDetail(String prodNum) throws NoProductException{
        Product product = productRepository.findById(prodNum).orElse(null);

        if(product != null){
            return modelMapper.map(product,ProductDTO.class);
        } else {
            throw new NoProductException("존재하지 않는 상품입니다.");
        }        
    }
}
