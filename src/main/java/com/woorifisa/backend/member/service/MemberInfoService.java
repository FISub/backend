package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.dto.MemberInfoDTO;

import jakarta.servlet.http.HttpSession;

public interface MemberInfoService {
    public MemberInfoDTO getMemberInfo(String memId);
    public String updateMemberInfo(MemberInfoDTO memberInfoDTO, String memNum);
} 
