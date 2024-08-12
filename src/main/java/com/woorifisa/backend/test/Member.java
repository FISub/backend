package com.woorifisa.backend.test;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String memId;
    private String memPw;
    private String memName;
    private String memEmail;
    private String memPhone;
    private String memSex;
    private Date memBirth;
    private String memAddr;
    private int memType;
}
