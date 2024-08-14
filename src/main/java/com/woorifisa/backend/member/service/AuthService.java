package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.dto.LoginResponseDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.LoginException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    public LoginResponseDTO login(String id, String pw, HttpServletRequest request) throws LoginException;

    public String join(MemberInfoDTO memberInfoDTO) throws JoinException;

    public boolean isValidSession(String sessionId, HttpSession session);

    public String logout();
}
