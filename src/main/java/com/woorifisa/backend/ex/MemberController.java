package com.woorifisa.backend.ex;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("/search")
    public ResponseEntity<MemberDTO> searchMemberById(@RequestParam Long id) {
        MemberDTO result = memberService.getMemberById(id); 
        return ResponseEntity.ok(result);
    }
    
}
