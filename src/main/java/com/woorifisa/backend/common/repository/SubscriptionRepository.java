package com.woorifisa.backend.common.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.dto.SubscriptionDTO;
import com.woorifisa.backend.common.entity.Member;
import com.woorifisa.backend.common.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

        List<Subscription> findByMemNum(Member member);

        @Query(value = "SELECT * from subscription where sub_deli = :curDate", nativeQuery = true)
        List<SubscriptionDTO> findByDeliveryDate(@Param("curDate") LocalDate curDate);

        @Modifying
        @Query(value = "UPDATE subscription SET sub_stat = 1, sub_deli = DATE_ADD(sub_deli, INTERVAL sub_per DAY), "
                        + "sub_upd = :curDate, sub_cnt = sub_cnt + 1, sub_paymentKey = :paymentKey WHERE sub_deli = :curDate", nativeQuery = true)
        int updateToInDelivery(@Param("curDate") LocalDate curDate, @Param("paymentKey") String paymentKey);

        @Modifying
        @Query(value = "UPDATE subscription SET sub_stat = 2, sub_upd = :curDate WHERE sub_stat = 1 AND sub_upd <= :twoDaysAgo", nativeQuery = true)
        void updateToDeliveryCompleted(@Param("curDate") LocalDate curDate, @Param("twoDaysAgo") LocalDate twoDaysAgo);

        @Modifying
        @Query(value = "UPDATE subscription SET sub_stat = 3, sub_upd = :curDate WHERE sub_stat = 2 AND sub_upd <= :oneDaysAgo", nativeQuery = true)
        void updateToWaiting(@Param("curDate") LocalDate curDate, @Param("oneDaysAgo") LocalDate oneDaysAgo);

        @Modifying
        @Query(value = "INSERT INTO subscription(sub_per, sub_start, sub_deli, sub_stat, sub_upd, sub_cnt, mem_num, prod_num, pay_num) "
                        + "VALUES(:subPer, :subStart, :subDeli, :subStat, :subUpd, :subCnt, :memNum, :prodNum, :payNum)", nativeQuery = true)
        public int subscriptionInsert(@Param("subPer") int subPer,
                        @Param("subStart") LocalDate subStart,
                        @Param("subDeli") LocalDate subDeli,
                        @Param("subStat") int subStat,
                        @Param("subUpd") LocalDate subUpd,
                        @Param("subCnt") int subCnt,
                        @Param("memNum") String memNum,
                        @Param("prodNum") String prodNum,
                        @Param("payNum") String payNum);

        @Modifying
        @Query(value = "DELETE FROM subscription WHERE sub_num = :subNum", nativeQuery = true)
        public int deleteSubById(@Param("subNum") String subNum);
}
