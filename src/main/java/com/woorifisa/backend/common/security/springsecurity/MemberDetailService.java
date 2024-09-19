package com.woorifisa.backend.common.security.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;

@Service
public class MemberDetailService implements UserDetailsService {
    
    @Autowired
    private MemberRepository memberRepository;
    

    @Override
    public MemberDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("넘어온 userId : "+username);
        Member member = memberRepository.findByMemId(username);
        if (member == null){
            System.out.println("user못찾음");
            throw new UsernameNotFoundException("User not found");
        }
        return new MemberDetail(member);
    }
}