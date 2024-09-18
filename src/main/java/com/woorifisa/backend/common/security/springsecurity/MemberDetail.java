package com.woorifisa.backend.common.security.springsecurity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.woorifisa.backend.common.entity.Member;



public class MemberDetail implements UserDetails {

    private final Member member;

    public MemberDetail(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    ArrayList<GrantedAuthority> auths = new ArrayList<>();
    for (int role : member.getMemTypeList()) {  // member.getMemTypeList()가 List<Integer>를 반환한다고 가정
        auths.add((GrantedAuthority) () -> String.valueOf(role) // int 값을 String으로 변환
        );
    }
    return auths;
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
