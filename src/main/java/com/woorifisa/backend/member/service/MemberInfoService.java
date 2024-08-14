package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.dto.MemberInfoDTO;


public interface MemberInfoService {
    public MemberInfoDTO getMemberInfo(String memId);
    public String updateMemberInfo(MemberInfoDTO memberInfoDTO, String memNum);
} 
