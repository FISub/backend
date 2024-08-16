package com.woorifisa.backend.member.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.entity.Subscription;
import com.woorifisa.backend.common.exception.NoDataExsistException;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.common.repository.ProductRepository;
import com.woorifisa.backend.common.repository.SubscriptionRepository;
import com.woorifisa.backend.member.dto.LoginSessionDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.dto.SubscriptionResponseDTO;

import jakarta.servlet.http.HttpSession;



@Service
public class MemberInfoServiceImpl implements MemberInfoService{

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    ProductRepository productRepository;

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

    @Override
    public List<SubscriptionResponseDTO> getSubList(HttpSession session) throws Exception {
        LoginSessionDTO sessionDTO = (LoginSessionDTO) session.getAttribute("login");
        Member member = memberRepository.findById(sessionDTO.getMemNum()).orElse(null);
        if (member == null){
            throw new NoDataExsistException("로그인 세션에 해당하는 user가 존재하지 않습니다.");
        }
        List<Subscription> subList = subscriptionRepository.findByMemNum(member);
        
        if (subList == null){
            throw new NoDataExsistException("구독 정보가 존재하지 않습니다.");
        }
        return subList.stream()
            .map(sub -> new SubscriptionResponseDTO(sub.getSubNum(), sub.getSubPer(), sub.getSubStart(), sub.getSubDeli(), sub.getSubStat(), sub.getSubUpd(), sub.getSubCnt(), member.getMemNum(), sub.getProdNum().getProdNum(), sub.getPayNum().getPayNum(), sub.getProdNum().getProdImg(), sub.getProdNum().getProdName()))
            .collect(Collectors.toList());
    }
    
    
             
    
}
