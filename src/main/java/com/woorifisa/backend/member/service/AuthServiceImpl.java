package com.woorifisa.backend.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.member.dto.JoinDTO;
import com.woorifisa.backend.member.exception.JoinException;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public String join(JoinDTO joinDTO) throws JoinException {
        String newMemID = joinDTO.getMemId();
        Member findedMember = memberRepository.findByMemId(newMemID);
        if (findedMember != null){
            throw new JoinException("이미 존재하는 ID입니다.");
        }
        if (joinDTO.getMemPw().length() < 4){
            throw new JoinException("4자 이상의 비밀번호를 입력해 주세요.");
        }

        try{
            memberRepository.insertMem(joinDTO.getMemName(), joinDTO.getMemId(), passwordEncoder.encode(joinDTO.getMemPw()), 
                                       joinDTO.getMemEmail(), joinDTO.getMemPhone(), joinDTO.getMemSex(), 
                                       joinDTO.getMemAddr(), joinDTO.getMemBirth(), joinDTO.getMemType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JoinException("회원가입 실패");
        }
        return "회원가입에 성공하였습니다.";
        
    }
}
