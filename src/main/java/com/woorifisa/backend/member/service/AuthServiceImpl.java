package com.woorifisa.backend.member.service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.member.LoginException;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberRepository memberRepository;

    private ModelMapper mapper = new ModelMapper(); 

    @Override
    public String login(String id, String pw, HttpSession session) throws LoginException {
        Member member = memberRepository.findByMemId(id).orElse(null);
        System.out.println(member);
        if (member != null) {
            if (pw.equals(member.getMemPw())) {
                MemberDTO dto = mapper.map(member, MemberDTO.class);
                System.out.println(dto);
                session.setAttribute("userId", id);
                session.setAttribute("userRole", member.getMemType());
                return "성공적으로 로그인 되었습니다.";
            } else {
                throw new LoginException("비밀번호가 다릅니다.");
            }
        } else {
            throw new LoginException("존재하지 않는 아이디 입니다.");
        }
    }



}
