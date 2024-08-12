package com.woorifisa.backend.ex;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

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
    

    // save() 를 통한 update
    @Transactional
    public void updateMemberName(Long id, String name){
        MemberEx member = memberRepository.findById(id).orElse(null);
        member.setName(name);
        memberRepository.save(member);
    }
}

