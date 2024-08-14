package com.woorifisa.backend.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.exception.SessionNotValidException;
import com.woorifisa.backend.member.dto.LoginSessionDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.service.AuthService;
import com.woorifisa.backend.member.service.MemberInfoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/member/info")
@RestController
public class MemberInfoController {

    @Autowired
    AuthService authService;
    @Autowired
    MemberInfoService memberInfoService;

    @GetMapping("/get")
    @Operation(summary = "유저 정보 조회 (개발 완료 - 그러나 예외 처리 추가 해야함)", description = "세션에 저장된 user 정보를 통하여 조회")
    public MemberInfoDTO getMemberInfo(HttpServletRequest request) throws SessionNotValidException {
        HttpSession session = request.getSession();
        
        if (authService.isValidSession(session)) {
            // 세션 아이디가 유효한 경우 처리 로직
            return memberInfoService.getMemberInfo(((LoginSessionDTO) session.getAttribute("login")).getMemId());
        } else {
            // 세션 아이디가 유효하지 않은 경우
            throw new SessionNotValidException("유효하지 않은 세션");
        }
    }

    @PutMapping("/update")
    @Operation(summary = "유저 정보 수정 (개발 완료 - 그러나 예외 처리 추가 해야함)", description = "user 로그인 정보 검증 후 정보 수정")
    public String updateMemberInfo(@RequestBody MemberInfoDTO memberInfoDTO, HttpServletRequest request) throws SessionNotValidException {
        HttpSession session = request.getSession();
        if (authService.isValidSession(session)){
            return memberInfoService.updateMemberInfo(memberInfoDTO, ((LoginSessionDTO)session.getAttribute("login")).getMemNum());
        } else {
            throw new SessionNotValidException("유효하지 않은 세션");
        }
        
    }
    
    
}
