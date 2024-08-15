package com.woorifisa.backend.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.MemberDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.manage.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


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
}
