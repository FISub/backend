package com.woorifisa.backend.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.ProductDTO;

@Service
public interface AdminService {
    public List<MemberDTO> memberAll();
    public List<ProductDTO> productAll();
    public String deleteMem(String memNum);
    public String deleteProd(String prodNum);
}