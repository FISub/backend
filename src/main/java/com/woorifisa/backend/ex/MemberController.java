package com.woorifisa.backend.ex;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Tag(name = "spring data jpa and swagger test")
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("/search")
    @Operation(summary = "id로 user 검색 (개발 완료)", description = "상세 설명 ~~~")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 검색을 성공했습니다."),
        @ApiResponse(responseCode = "4xx", description = "사용자 검색을 실패했습니다.")
    })
    public ResponseEntity<MemberDTO> searchMemberById(@RequestParam Long id) {
        MemberDTO result = memberService.getMemberById(id); 
        return ResponseEntity.ok(result);
    }



    @DeleteMapping("/delete")
    @Operation(summary = "id로 user 삭제 (개발 예정)", description = "상세 설명 ~~~")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "사용자 삭제를 성공했습니다."),
        @ApiResponse(responseCode = "4xx", description = "사용자 삭제를 실패했습니다.")
    })
    public void swaggerExample1() {

    }

    @PostMapping("/join")
    @Operation(summary = "회원 가입 (개발 중 - 이승준)", description = "상세 설명 ~~~")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입에 성공했습니다."),
        @ApiResponse(responseCode = "4xx", description = "회원가입에 실패했습니다.")
    })
    public void swaggerExample2() {

    }

    @GetMapping("/getall")
    @Operation(summary = "모든 유저 불러오기 (개발 예정)", description = "상세 설명 ~~~")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "모둔 유저를 불러오는데 성공했습니다."),
        @ApiResponse(responseCode = "4xx", description = "모둔 유저를 불러오는데 실패했습니다.")
    })
    public void swaggerExample3() {

    }
    
}
