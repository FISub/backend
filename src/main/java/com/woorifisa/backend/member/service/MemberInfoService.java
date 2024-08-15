package com.woorifisa.backend.member.service;

import java.util.List;
import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;

import jakarta.servlet.http.HttpSession;


public interface MemberInfoService {
    public MemberInfoDTO getMemberInfo(String memId);
    public String updateMemberInfo(MemberInfoDTO memberInfoDTO, String memNum);
    public List<SubscriptionDTO> getSubList(HttpSession session) throws Exception;
} 
