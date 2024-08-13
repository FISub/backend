package com.woorifisa.backend.member.service;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.LoginException;

import jakarta.servlet.http.HttpSession;

public interface AuthService {
    public String login(String id, String pw, HttpSession session) throws LoginException;
    public String join(MemberDTO memberDTO) throws JoinException;

}
