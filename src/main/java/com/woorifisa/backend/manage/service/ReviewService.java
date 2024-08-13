package com.woorifisa.backend.manage.service;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.ReviewDTO;

@Service
public interface ReviewService {
    public String insertRev(ReviewDTO dto);
}
