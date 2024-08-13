package com.woorifisa.backend.common.entity;

import java.util.Date;

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
    private Date memBirth;
    @NotNull
    private String memAddr;
    @NotNull
    private int memType;
}
