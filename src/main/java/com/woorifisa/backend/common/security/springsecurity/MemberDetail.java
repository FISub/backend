package com.woorifisa.backend.common.security.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.woorifisa.backend.common.entity.Member;



public class MemberDetail implements UserDetails {

    private final Member member;

    public MemberDetail(Member member) {
        this.member = member;
    }

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // memType에 따라 역할을 다르게 설정
        if (member.getMemType() == 1) {
            authorities.add(new SimpleGrantedAuthority("1"));
        } else if (member.getMemType() == 2) {
            authorities.add(new SimpleGrantedAuthority("2"));
        } else if (member.getMemType() == 9) {
            authorities.add(new SimpleGrantedAuthority("9"));
        }
        // 추가적인 memType에 대한 조건 처리
        
        return authorities;
    }

    @Override
    public String getPassword() {
    System.out.println("PW : " + member.getMemPw());
       return member.getMemPw();
    }

    @Override
    public String getUsername() {
        System.out.println("ID : " + member.getMemId());
        return member.getMemId();
    }

    public String getMemNum() {
        return member.getMemNum();
    }

    public String getMemType() {
        return String.valueOf(member.getMemType());
    }

    // 계정 만료여부 제공
    // 특별히 사용을 안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정 비활성화 여부 제공
    // 특별히 사용 안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 계정 인증 정보를 항상 저장할지에 대한 여부
    // true 처리할시 모든 인증정보를 만료시키지 않기에 주의해야한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정의 활성화 여부
    // 딱히 사용안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isEnabled() {
        return true;
    }
}
