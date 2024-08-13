package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.SubscriptionDTO;

@Service
public interface TestService {
    public MemberDTO login(Map<String, String> reqMap) throws LoginException;
    public String updateMem(Map<String, String> reqMap);
    public String insertMem(MemberDTO dto);
    public String insertSub(SubscriptionDTO dto);
}
