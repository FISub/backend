package com.woorifisa.backend.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.manage.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/admin")
@Tag(name = "admin controller")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 모든 member 불러오기
    @GetMapping("/memberAll")
    @Operation(summary = "관리자 페이지에서 전체 회원 불러오기")
    public List<MemberDTO> memberAll() {
        return adminService.memberAll();
    }

    // 모든 product 불러오기
    @GetMapping("/productAll")
    @Operation(summary = "관리자 페이지에서 전체 상품 불러오기")
    public List<ProductDTO> productAll() {
        return adminService.productAll();
    }

    @PostMapping("/deleteMem")
    @Operation(summary = "회원 삭제하기",
               description = "결제, 구독, 등록상품 삭제 처리 / 리뷰 null 처리")
    public String deleteMem(@RequestParam(value = "memNum") String memNum) {
        return adminService.deleteMem(memNum);
    }
    
    @PostMapping("/deleteProd")
    @Operation(summary = "상품 삭제하기",
               description = "구독, 리뷰 삭제 처리")
    public String deleteProd(@RequestParam(value = "prodNum") String prodNum) {
        return adminService.deleteProd(prodNum);
    }
    
}
