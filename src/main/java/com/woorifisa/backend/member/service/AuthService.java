package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.LoginException;

import jakarta.servlet.http.HttpSession;

public interface AuthService {
    public String login(String id, String pw, HttpSession session) throws LoginException;


}
