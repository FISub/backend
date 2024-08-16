package com.woorifisa.backend.member.service;

import java.util.List;

import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.dto.SubscriptionResponseDTO;

import jakarta.servlet.http.HttpSession;


public interface MemberInfoService {
    public MemberInfoDTO getMemberInfo(String memId);
    public String updateMemberInfo(MemberInfoDTO memberInfoDTO, String memNum);
    public List<SubscriptionResponseDTO> getSubList(HttpSession session) throws Exception;
    public String deleteSub(String subNum) throws Exception;
} 
