package com.woorifisa.backend.ex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class MemberDTO {
    private Long id;

    private String Birth;

    private String email;

    private String name;
}
