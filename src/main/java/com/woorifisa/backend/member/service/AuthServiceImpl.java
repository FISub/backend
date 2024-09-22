package com.woorifisa.backend.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.exception.JoinException;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public String join(MemberInfoDTO memberInfoDTO) throws JoinException {
        String newMemID = memberInfoDTO.getMemId();
        Member findedMember = memberRepository.findByMemId(newMemID);
        if (findedMember != null){
            throw new JoinException("이미 존재하는 ID");
        }

        try{
            memberRepository.insertMem(memberInfoDTO.getMemName(), memberInfoDTO.getMemId(), passwordEncoder.encode(memberInfoDTO.getMemPw()), 
                                       memberInfoDTO.getMemEmail(), memberInfoDTO.getMemPhone(), memberInfoDTO.getMemSex(), 
                                       memberInfoDTO.getMemAddr(), memberInfoDTO.getMemBirth(), memberInfoDTO.getMemType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JoinException("회원가입 실패");
        }
        return "회원가입에 성공하였습니다.";
        
    }
}
