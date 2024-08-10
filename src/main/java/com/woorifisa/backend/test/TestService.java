package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    
    @Autowired
    MemberDAO dao;

    public String login(Map<String, String> reqMap){
        return dao.login(reqMap);
    }
}
