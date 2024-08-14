package com.woorifisa.backend.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.repository.MemberRepository;
import com.woorifisa.backend.member.dto.LoginResponseDTO;
import com.woorifisa.backend.member.dto.LoginSessionDTO;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.LoginException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service

public class AuthServiceImpl implements AuthService {

    @Autowired
    MemberRepository memberRepository;

    private ModelMapper mapper = new ModelMapper(); 

    @Override
    public LoginResponseDTO login(String id, String pw, HttpServletRequest request) throws LoginException {
        Member member = memberRepository.findByMemId(id).orElse(null);
        System.out.println(member);
        if (member != null) {
            if (pw.equals(member.getMemPw())) {
                LoginSessionDTO login = mapper.map(member, LoginSessionDTO.class);
                System.out.println(login);
                HttpSession session = request.getSession();
                System.out.println("저장된 세션 id: "+ session.getId() );
                session.setAttribute("login", login);

                System.out.println("저장된 세션 : "+session.getAttribute("login")); 

                return LoginResponseDTO.builder()
                                       .memNum(member.getMemNum())
                                       .memId(id)
                                       .memType(member.getMemType())
                                       .build();
                                                                            
            } else {
                throw new LoginException("비밀번호가 다릅니다.");
            }
        } else {
            throw new LoginException("존재하지 않는 아이디 입니다.");
        }
    }

    @Transactional
    @Override
    public String join(MemberInfoDTO memberInfoDTO) throws JoinException {
        try{
            memberRepository.insertMem(memberInfoDTO.getMemName(), memberInfoDTO.getMemId(), memberInfoDTO.getMemPw(), 
                                       memberInfoDTO.getMemEmail(), memberInfoDTO.getMemPhone(), memberInfoDTO.getMemSex(), 
                                       memberInfoDTO.getMemAddr(), memberInfoDTO.getMemBirth(), memberInfoDTO.getMemType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JoinException("회원가입 실패");
        }
        return "회원가입에 성공하였습니다.";
        
    }

    @Override
    public boolean isValidSession(String sessionId, HttpSession session){
        if (sessionId == null || !sessionId.equals(session.getId())) {
            return false;
        } else {
            // 세션에 userId 속성이 있는지 확인하여 유효성을 판단
            String userId = (String) session.getAttribute("userId");
            return userId != null;
        }        
    }

    @Override
    public String logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }
}
