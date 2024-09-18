package com.woorifisa.backend.member.service;

import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.exception.JoinException;

public interface AuthService {

    public String join(MemberInfoDTO memberInfoDTO) throws JoinException;
}
