package com.woorifisa.backend.common.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.woorifisa.backend.common.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByMemId(String memId);

    @Modifying
    @Query(value = "update member set mem_id = :memId where mem_num = :memNum", nativeQuery = true)
    public int updateByMemnumMemId(@Param("memNum") String memNum, @Param("memId") String memId);

    @Modifying
    @Query(value = "insert into member(mem_id, mem_pw, mem_name, mem_email, mem_phone, mem_sex, mem_addr, mem_birth, mem_type) values(:id, :pw,:name, :email,:phone,:sex,:addr,:birth,:type)", nativeQuery = true)
    public int insertMem(@Param("name") String name,
            @Param("id") String id,
            @Param("pw") String pw,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("sex") String sex,
            @Param("addr") String addr,
            @Param("birth") Date birth,
            @Param("type") int type);
}