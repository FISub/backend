package com.woorifisa.backend.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woorifisa.backend.common.dto.PaymentDTO;
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
