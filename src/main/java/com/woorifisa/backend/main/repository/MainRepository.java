package com.woorifisa.backend.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.entity.Payment;

@Repository
public interface MainRepository extends JpaRepository<Payment, String> {
    
    @Modifying
    @Query(value= "insert into payment(mem_num, pay_card, pay_exp, pay_cvc, pay_pw) values(:memNum, :card, :exp, :cvc, :pw)", nativeQuery = true)
    public int insertCard(@Param("memNum") String memNum,
                              @Param("card") String card,
                              @Param("exp") String exp,
                              @Param("cvc") int cvc,
                              @Param("pw") int pw);

}
