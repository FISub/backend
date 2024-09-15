package com.woorifisa.backend.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    PasswordEncoder passwordEncoder;

    private ModelMapper mapper = new ModelMapper(); 

    @Override
    public LoginResponseDTO login(String id, String pw, HttpServletRequest request) throws LoginException {
        Member member = memberRepository.findByMemId(id);
        System.out.println(member);
        if (member != null) {
            if (member.checkPassword(pw, passwordEncoder)) {
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

    @Override
    public boolean isValidSession(HttpSession session){
        System.out.println(session.getId());
        LoginSessionDTO loginSession = (LoginSessionDTO) session.getAttribute("login");
        if (loginSession == null || loginSession.getMemId() == null || loginSession.getMemNum() == null || loginSession.getMemType() == 0) {
            
            return false;
        } else {
            return true;
        }        
    }

    @Override
    public String logout(HttpSession session){
        try{
            session.invalidate();
    
            return "로그아웃 완료";
        } catch (Exception e) {
            return "로그아웃 실패";
        }
        
    }
}
