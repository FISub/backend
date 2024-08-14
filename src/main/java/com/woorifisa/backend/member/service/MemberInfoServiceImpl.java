package com.woorifisa.backend.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.member.dto.MemberInfoDTO;



@Service
public class MemberInfoServiceImpl implements MemberInfoService{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public MemberInfoDTO getMemberInfo(String memId) {
            
            Member member= memberRepository.findByMemId(memId);
            return MemberInfoDTO.builder()
                                .memAddr(member.getMemAddr())
                                .memBirth(member.getMemBirth())
                                .memEmail(member.getMemEmail())
                                .memId(member.getMemId())
                                .memName(member.getMemName())
                                .memPhone(member.getMemPhone())
                                .memPw(member.getMemPw())
                                .memSex(member.getMemSex())
                                .memType(member.getMemType())
                                .build();
    }
    
    @Transactional
    @Override
    public String updateMemberInfo(MemberInfoDTO memberInfoDTO, String memNum) {
        try {
            memberRepository.updateMemberInfo(memNum, memberInfoDTO.getMemId(), memberInfoDTO.getMemPw(),memberInfoDTO.getMemName(), memberInfoDTO.getMemEmail(),memberInfoDTO.getMemPhone(),memberInfoDTO.getMemSex(),memberInfoDTO.getMemBirth(),memberInfoDTO.getMemAddr(),memberInfoDTO.getMemType());
            return "수정 성공";
        } catch (Exception e) {
            return null;
        }
        
        
    }          
             
    
}
