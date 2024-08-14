package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.SubscriptionDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "login test")
public class TestController {

    @Autowired
    TestService testService;

    // 로그인(test)
    @PostMapping("/login")
    @Operation(summary = "로그인 테스트 (개발 완료)", description = "상세 설명 ~~~")
    public MemberDTO login(@RequestBody Map<String, String> reqMap) throws LoginException{
        return testService.login(reqMap);         
    }
    
    // id 수정(test)
    @PostMapping("/updateMem")
    @Operation(summary = "memberID 수정 (개발 완료)", description = "상세 설명 ~~~")
    public String updateMem(@RequestBody Map<String, String> reqMap) {        
        return testService.updateMem(reqMap);
    }
    
    @ExceptionHandler
    public String nonLogin(LoginException e){
        e.printStackTrace();
        return e.getMessage();
    }

    @PostMapping("/insertMem")
    @Operation(summary = "member 추가 (개발 완료)", description = "상세 설명 ~~~")
    public String insertMem(@RequestBody MemberDTO dto) {              
        return testService.insertMem(dto);
    }

    @PostMapping("/insertSub")
    @Operation(summary = "subscription 추가 (개발 완료)", description = "상세 설명 ~~~")
    public String postMethodName(@RequestBody SubscriptionDTO dto) {
        
        return testService.insertSub(dto);
    }
    
    
}
