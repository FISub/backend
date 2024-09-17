package com.woorifisa.backend.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    

    // main --------------------------------------------------------------------------------------------------------------------------------------------------
    @Query(value = "select pay_num, pay_card, pay_brand from payment where mem_num = :memNum",nativeQuery = true)
    public List<Object[]> paymentAllByMember(@Param("memNum") String memNum);

    @Modifying
    @Query(value= "insert into payment(mem_num, pay_card, pay_billingKey, pay_brand) values(:memNum, :card, :billingKey, :brand)", nativeQuery = true)
    public int insertCard(@Param("memNum") String memNum,
                          @Param("card") String card,
                          @Param("billingKey") String billingKey,
                          @Param("brand") String brand);

}
