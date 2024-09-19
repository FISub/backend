package com.woorifisa.backend.product.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.security.springsecurity.MemberDetail;
import com.woorifisa.backend.product.service.ProductService;


@RestController
@RequiredArgsConstructor
@Tag(name = "Product API")
public class ProductController {

    private final ProductService productService;

    // 상품 등록 (Insert)
    @PostMapping("/products")
    @Operation(summary = "상품 등록", description = "새로운 상품을 등록합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상품 등록 성공"),
        @ApiResponse(responseCode = "4xx", description = "상품 등록 실패")
    })
    public String insertProduct(@RequestBody ProductDTO productDTO) {
        return productService.insertProduct(productDTO);
    }

    // 상품 수정 (Update)
    @PutMapping("/products/{prodNum}")
    @Operation(summary = "상품 수정", description = "기존 상품의 정보를 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "상품 수정 성공"),
        @ApiResponse(responseCode = "4xx", description = "상품 수정 실패")
    })
    public String updateProduct(@PathVariable String prodNum, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(prodNum, productDTO);
    }

    @GetMapping("/productAllBiz")
    @Operation(summary = "상품 조회", description = "로그인한 사업자가 등록한 상품을 조회합니다.")
    public List<ProductDTO> productAllBiz(@AuthenticationPrincipal MemberDetail memberDetail) {
        return productService.productAll(memberDetail.getMemNum());
    }
    
}
