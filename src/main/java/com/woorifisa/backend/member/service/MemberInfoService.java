package com.woorifisa.backend.member.service;

import java.util.List;

import com.woorifisa.backend.common.exception.SessionNotValidException;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.dto.MemberInfoEditDTO;
import com.woorifisa.backend.member.dto.SubscriptionResponseDTO;
import com.woorifisa.backend.member.exception.NotValidPasswordException;


public interface MemberInfoService {
    public MemberInfoDTO getMemberInfo(String memId);
    public String updateMemberInfo(MemberInfoEditDTO memberInfoEditDTO, String memNum) throws NotValidPasswordException, SessionNotValidException;
    public List<SubscriptionResponseDTO> getSubList(String memNum) throws Exception;
    public String deleteSub(String subNum) throws Exception;
} 
