package com.woorifisa.backend.test;

import java.util.Map;

public interface TestService {
    public MemberDTO login(Map<String, String> reqMap) throws LoginException;
    public String updateMem(Map<String, String> reqMap);
}
