package com.woorifisa.backend.common.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "subscription")
public class Subscription {
    @Id
    private String subNum;
    @NotNull
    private int subPer;
    private LocalDate subStart;
    private LocalDate subDeli;
    @NotNull
    private int subStat;
    private LocalDate subUpd;
    private int subCnt;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "mem_num")
    private Member memNum;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "prod_num")
    private Product prodNum;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "pay_num")
    private Payment payNum;
    @NotNull
    @Column(name = "sub_paymentKey")
    private String subPaymentKey;
}
