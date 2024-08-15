package com.woorifisa.backend.common.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<com.woorifisa.backend.common.entity.Subscription, String>{

    @Modifying
    @Query(value = "insert into subscription(sub_per, sub_start, sub_deli, sub_stat, sub_upd, sub_cnt, mem_num, prod_num, pay_num)" 
                    +"values(:subPer, :subStart, :subDeli, :subStat, :subUpd, :subCnt, :memNum, :prodNum, :payNum)", nativeQuery = true)
    public int insertSub(@Param("subPer") int subPer,
                         @Param("subStart") Date subStart,
                         @Param("subDeli") Date subDeli,
                         @Param("subStat") int subStat,
                         @Param("subUpd") Date subUpd,
                         @Param("subCnt") int subCnt,
                         @Param("memNum") String memNum,
                         @Param("prodNum") String prodNum,
                         @Param("payNum") String payNum);


    List<Subscription> findByMemNum(Member member);
}
