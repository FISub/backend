package com.woorifisa.backend.test;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository2 extends JpaRepository<Member, String>{
    Optional<Member> findByMemId(String memId);
    @Modifying
    @Query(value= "update member set mem_id = :memId where mem_num = :memNum", nativeQuery = true)
    public int updateByMemnumMemId(@Param("memNum") String memNum, @Param("memId") String memId);
}
