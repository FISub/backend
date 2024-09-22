package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.dto.JoinDTO;
import com.woorifisa.backend.member.exception.JoinException;

public interface AuthService {

    public String join(JoinDTO joinDTO) throws JoinException;
}
