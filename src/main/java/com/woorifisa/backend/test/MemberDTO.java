package com.woorifisa.backend.test;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDTO {
    private String mem_num;
    private String mem_id;
    private String mem_pw;
    private String mem_name;
    private String mem_email;
    private String mem_phone;
    private String mem_sex;
    private Date mem_birth;
    private String mem_addr;
    private int mem_type;
}
