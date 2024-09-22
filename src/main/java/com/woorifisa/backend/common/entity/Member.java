package com.woorifisa.backend.common.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "member")
public class Member {    
    @Id
    private String memNum;
    @NotNull
    private String memId;
    @NotNull
    private String memPw;
    @NotNull
    private String memName;
    @NotNull
    private String memEmail;
    @NotNull
    private String memPhone;
    @NotNull
    private String memSex;
    @NotNull
    private LocalDate memBirth;
    @NotNull
    private String memAddr;
    @NotNull
    private int memType;



    // 입력된 평문형식의 pw와 db에 저장된 암호화된 pw 비교
    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
    return passwordEncoder.matches(plainPassword, this.memPw);
    }
}
