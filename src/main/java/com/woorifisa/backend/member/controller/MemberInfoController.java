package com.woorifisa.backend.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.woorifisa.backend.common.exception.SessionNotValidException;
import com.woorifisa.backend.common.security.springsecurity.MemberDetail;
import com.woorifisa.backend.member.dto.MemberInfoDTO;
import com.woorifisa.backend.member.dto.MemberInfoEditDTO;
import com.woorifisa.backend.member.dto.SubscriptionResponseDTO;
import com.woorifisa.backend.member.exception.NotValidPasswordException;
import com.woorifisa.backend.member.service.AuthService;
import com.woorifisa.backend.member.service.MemberInfoService;

import io.swagger.v3.oas.annotations.Operation;



@RequestMapping("/member")
@RestController
public class MemberInfoController {

    @Autowired
    AuthService authService;
    @Autowired
    MemberInfoService memberInfoService;

    @GetMapping("/info/get")
    @Operation(summary = "유저 정보 조회 (개발 완료 - 그러나 예외 처리 추가 해야함)", description = "세션에 저장된 user 정보를 통하여 조회")
    public MemberInfoDTO getMemberInfo(@AuthenticationPrincipal MemberDetail memberDetail) throws SessionNotValidException {
        return memberInfoService.getMemberInfo(memberDetail.getUsername());   
    }

    @PutMapping("/info/update")
    @Operation(summary = "유저 정보 수정 (개발 완료 - 그러나 예외 처리 추가 해야함)", description = "user 로그인 정보 검증 후 정보 수정")
    public String updateMemberInfo(@RequestBody MemberInfoEditDTO memberInfoEditDTO, @AuthenticationPrincipal MemberDetail memberDetail) throws NotValidPasswordException, SessionNotValidException {
        return memberInfoService.updateMemberInfo(memberInfoEditDTO, memberDetail.getMemNum()); 
    }
    
    @GetMapping("/sublist/get")
    @Operation(summary = "유저 구독 정보 조회 (개발 완료)", description = "user 로그인 정보 검증 후 정보 수정")
    public List<SubscriptionResponseDTO> getSubList(@AuthenticationPrincipal MemberDetail memberDetail) throws Exception {
        return memberInfoService.getSubList(memberDetail.getMemNum());
    }

    @DeleteMapping("/sublist/delete")
    @Operation(summary = "구독 취소 (개발 완료)", description = "member의 상품 구독 취소")
    public String deleteSub(@RequestParam("subNum") String subNum) throws Exception {
        return memberInfoService.deleteSub(subNum);
    }
    
}
