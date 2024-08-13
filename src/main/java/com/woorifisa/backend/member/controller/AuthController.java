package com.woorifisa.backend.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.member.dto.LoginDTO;
import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.LoginException;
import com.woorifisa.backend.member.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/member")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    // 로그인(test)
    @PostMapping("/login")
    @Operation(summary = "로그인 (개발 중)", description = "로그인 성공 시 id와 권한 정보를 session에 저장")
    public String login(@RequestBody LoginDTO loginDTO, HttpSession session, HttpServletResponse response) throws LoginException{
        String message = authService.login(loginDTO.getId(), loginDTO.getPw(),session);         
        response.addCookie(new jakarta.servlet.http.Cookie("JSESSIONID", session.getId()));
        return message;
    }

    @PostMapping("/join")
    @Operation(summary = "회원가입 (개발 완료)", description = "회원가입 시 회원 정보를 저장")
    public String join(@RequestBody MemberDTO memberDTO) throws JoinException{
        return authService.join(memberDTO);
    }
}
