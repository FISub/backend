package com.woorifisa.backend.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.member.dto.LoginRequestDTO;
import com.woorifisa.backend.member.dto.LoginResponseDTO;
import com.woorifisa.backend.member.dto.LoginSessionDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.LoginException;
import com.woorifisa.backend.member.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    // 로그인(test)
    @PostMapping("/login")
    @Operation(summary = "로그인 (개발 중)", description = "로그인 성공 시 id와 권한 정보를 session에 저장")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginDTO, HttpServletRequest request) throws LoginException{
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO.getId(), loginDTO.getPw(), request);  
        return loginResponseDTO;
    }

    @PostMapping("/join")
    @Operation(summary = "회원가입 (개발 완료)", description = "회원가입 시 회원 정보를 저장")
    public String join(@RequestBody MemberInfoDTO memberInfoDTO) throws JoinException{
        return authService.join(memberInfoDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃 (개발 중)", description = "로그인 성공 시 id와 권한 정보를 session에 저장")
    public String logout(@RequestBody int memNum,  
                                  @ModelAttribute("loginSession")  LoginSessionDTO loginSession) throws LoginException{ 
        return null;
    }

    @GetMapping("/sessionInfo")
    @Operation(summary = "세션 정보 조회", description = "현재 로그인된 사용자의 세션 정보를 반환합니다.")
    public LoginSessionDTO getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("클라이언트 요청 세션 id : " + session.getId());
          // 세션에서 로그인 정보를 가져오기
        System.out.println("Session: " + session.getAttribute("login"));
      
        LoginSessionDTO login = (LoginSessionDTO) session.getAttribute("login");

        System.out.println(login);
        if (login != null) {
            return login;
        } else {
            return null; 
        }
    }
}
