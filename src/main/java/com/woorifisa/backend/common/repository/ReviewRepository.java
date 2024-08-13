package com.woorifisa.backend.common.repository;

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

    @Modifying
    @Query(value = "insert into review(rev_star, rev_cont, mem_num, prod_num) values(:star, :content, :memNum, :prodNum)", nativeQuery = true)
    public int insertRev(@Param("star") int star,
                    @Param("content") String content,
                    @Param("memNum") String memNum,
                    @Param("prodNum") String prodNum
    );
}
