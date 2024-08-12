package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class TestController {

    @Autowired
    TestService testService;

    // 로그인(test)
    @PostMapping("/login")
    public MemberDTO login(@RequestBody Map<String, String> reqMap) throws LoginException{
        return testService.login(reqMap);         
    }
    
    // id 수정(test)
    @PostMapping("/updateMem")
    public String updateMem(@RequestBody Map<String, String> reqMap) {        
        return testService.updateMem(reqMap);
    }
    
    @ExceptionHandler
    public String nonLogin(LoginException e){
        e.printStackTrace();
        return e.getMessage();
    }
}
