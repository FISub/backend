package com.woorifisa.backend.manage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.entity.Product;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.common.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public List<MemberDTO> memberAll() {
        List<Member> member = memberRepository.memberAll();

        List<MemberDTO> dtoList = member.stream()
                .map(mem -> modelMapper.map(mem, MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    @Transactional
    public List<ProductDTO> productAll() {
        List<Product> product = productRepository.productAll();

        List<ProductDTO> dtoList = product.stream()
                .map(prod -> modelMapper.map(prod, ProductDTO.class))
                .collect(Collectors.toList());
      return dtoList;
    }

    @Override
    @Transactional
    public String deleteMem(String memNum) {
        int result = memberRepository.deleteMem(memNum);

        if (result == 1) {
            return "member delete success";
        }
        return "member delete fail";
    }
    
    @Override
    @Transactional
    public String deleteProd(String prodNum) {
        int result = productRepository.deleteProd(prodNum);

        if (result == 1) {
            return "product delete success";
        }
        return "product delete fail";
    }
}
