package com.woorifisa.backend.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.PaymentDTO;
import com.woorifisa.backend.common.dto.ProductDTO;
import com.woorifisa.backend.common.dto.ReviewDTO;
import com.woorifisa.backend.main.dto.ReviewPrintDTO;
import com.woorifisa.backend.main.exception.NoProductException;
import com.woorifisa.backend.main.service.MainService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;




@RestController
@RequestMapping("/main")
@Tag(name = "main controller")
public class MainController {
    
    @Autowired
    private MainService mainService;

    // main 상품 미리보기
    @GetMapping("/productPreview")
    public List<ProductDTO> productPreview() {
        return mainService.productPreview();
    }
    
    // 상품 리스트 보기(카테고리별, 없을 시 전체)
    @GetMapping("/productAll")
    public List<ProductDTO> productAllByCategory(@RequestParam(value = "category", defaultValue  = "-99999") int category) {
        return mainService.productAllByCategory(category);
    }
    
    // 상품 상세보기
    @GetMapping("/productDetail/{prodNum}")
    public ProductDTO productDetail(@PathVariable("prodNum") String prodNum) throws NoProductException {
        return mainService.productDetail(prodNum);
    }
    
    // 상품 리뷰list 출력
    @GetMapping("/review/{prodNum}")
    public List<ReviewPrintDTO> reviewAllByProdNum(@PathVariable("prodNum") String prodNum) {
        return mainService.reviewAllByProdNum(prodNum);
    }
    
    // 리뷰 등록
    @PostMapping("/reviewInsert")
    public ReviewPrintDTO reviewInsert(@RequestBody ReviewDTO dto) { 
        return mainService.reviewInsert(dto);
    }
    

    // 결제 정보 추가
    @PostMapping("/insertCard")
    @Operation(summary = "결제방식 추가 (test)", description = "결제 방식 추가")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "결제방식 추가 성공"),
        @ApiResponse(responseCode = "4xx", description="결제방식 추가 실패")
    })
    public String insertCard(@RequestBody PaymentDTO dto) {        
        return mainService.insertCard(dto);
    }
    
}
