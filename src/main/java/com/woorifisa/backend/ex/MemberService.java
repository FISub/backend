package com.woorifisa.backend.ex;


public interface MemberService {

    public MemberDTO getMemberById(Long id);

    public void updateMemberName(Long id, String name);

}

