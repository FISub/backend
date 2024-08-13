package com.woorifisa.backend.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.manage.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Override
    @Transactional
    public String insertRev(ReviewDTO dto) {
        int result = repository.insertRev(dto.getRevStar(), dto.getRevCont(), dto.getMemNum(), dto.getProdNum());

        if(result == 1){
            return "insert success";
        }
        return "insert fail";
    }
}
