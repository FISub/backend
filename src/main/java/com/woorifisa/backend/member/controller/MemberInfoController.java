package com.woorifisa.backend.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.exception.SessionNotValidException;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RequestMapping("/member/info")
@RestController
public class MemberInfoController {

    @Autowired
    AuthService authService;

    @GetMapping("/get")
    @Operation(summary = "유저 정보 조회 (개발 중)", description = "로그인 성공 시 id와 권한 정보를 session에 저장")
    public String getMemberInfo(@RequestHeader("LoginSessionID") String loginSessionId, @RequestParam String userId, HttpSession session) throws SessionNotValidException {
        if (authService.isValidSession(loginSessionId, session)) {
            // 세션 아이디가 유효한 경우 처리 로직
            return "Valid session for userId: " + userId;
        } else {
            // 세션 아이디가 유효하지 않은 경우
            throw new SessionNotValidException("유효하지 않은 세션");
        }
    }

    @PostMapping("/update")
    public String postMethodName(@RequestBody MemberInfoDTO memberInfoDTO) {
        return null;
    }
    
    
}
