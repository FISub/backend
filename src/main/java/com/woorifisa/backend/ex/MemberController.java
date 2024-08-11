package com.woorifisa.backend.ex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("/search")
    public ResponseEntity<MemberDTO> searchMemberById(@RequestParam Long id) {
        MemberDTO result = memberService.getMemberById(id); 
        return ResponseEntity.ok(result);
    }
    
}
