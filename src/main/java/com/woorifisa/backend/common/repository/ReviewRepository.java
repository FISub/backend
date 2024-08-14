package com.woorifisa.backend.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woorifisa.backend.common.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findByrevNum(String revNum);

    // main
    // ----------------------------------------------------------------------------------------------------------------------------------------

    // 상품에 대한 리뷰 가져오기
    @Query(value = "select rev_num, r.mem_num, rev_star, rev_cont, m.mem_name from review r join member m on r.mem_num= m.mem_num where prod_num = :prodNum order by r.rev_num desc", nativeQuery = true)
    public List<Object[]> reviewAllByProdNum(@Param("prodNum") String prodNum);

    // 상품에 대한 리뷰 추가
    @Modifying
    @Query(value = "insert into review(rev_star, rev_cont, mem_num, prod_num) values(:star, :content, :memNum, :prodNum)", nativeQuery = true)
    public int reviewInsert(@Param("star") int star,
            @Param("content") String content,
            @Param("memNum") String memNum,
            @Param("prodNum") String prodNum);

    // 최신 리뷰를 가져오는 쿼리 메서드
    @Query(value = "select rev_num, r.mem_num, rev_star, rev_cont, m.mem_name from review r join member m on r.mem_num= m.mem_num ORDER BY r.rev_num DESC limit 1", nativeQuery = true)
    public List<Object[]> findTopByOrderByRevNumDesc();
}
