package com.woorifisa.backend.test;

import java.util.Map;

import com.woorifisa.backend.common.dto.MemberDTO;

public interface TestService {
    public MemberDTO login(Map<String, String> reqMap) throws LoginException;
    public String updateMem(Map<String, String> reqMap);
}
