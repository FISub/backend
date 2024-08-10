package com.woorifisa.backend.ex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;


    public MemberDTO getMemberById(Long id) {
        MemberEx member = memberRepository.findById(id).orElse(null);
        System.out.println(member);
        return MemberDTO.builder()
                        .id(member.getId())
                        .Birth(member.getBirth())
                        .name(member.getName())
                        .email(member.getEmail())
                        .build();
    }
    
}

