package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "login test")
@RequestMapping("/user")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "상세 설명 ~~~")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다."),
        @ApiResponse(responseCode = "4xx", description = "비밀번호가 틀렸습니다."),
        @ApiResponse(responseCode = "4xx", description = "존재하지 않는 id입니다.")
    })
    public String login(@RequestBody Map<String, String> reqMap) {
        return testService.login(reqMap);
         
    }

}
