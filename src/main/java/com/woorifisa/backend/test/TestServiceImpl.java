package com.woorifisa.backend.test;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    MemberRepository2 repository;

    private ModelMapper mapper = new ModelMapper();

    public MemberDTO login(Map<String, String> reqMap) throws LoginException{
        Member member = repository.findByMemId(reqMap.get("id")).orElse(null);
        System.out.println(member);
        if (member != null) {
            if (reqMap.get("pw").equals(member.getMemPw())) {
                MemberDTO dto = mapper.map(member, MemberDTO.class);
                System.out.println(dto);
                return dto;
            } else {
                throw new LoginException("비밀번호가 다릅니다.");
            }
        } else {
            throw new LoginException("존재하지 않는 아이디 입니다.");
        }
    }

    @Override
    @Transactional
    public String updateMem(Map<String, String> reqMap) {
        int result = repository.updateByMemnumMemId(reqMap.get("mem_num"), reqMap.get("mem_id"));
        if(result != 1){
            return "수정 실패";
        }
        return "수정 성공";
    }
}
