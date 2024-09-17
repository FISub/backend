package com.woorifisa.backend.common.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woorifisa.backend.common.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByMemId(String memId);

    @Query(value = "select * from member order by mem_num", nativeQuery = true)
    public List<Member> memberAll();

    @Modifying
    @Query(value = "delete from member where mem_num = :memNum", nativeQuery = true)
    public int deleteMem(@Param("memNum") String memNum);

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
            @Param("birth") LocalDate birth,
            @Param("type") int type);

    @Modifying
    @Query(value = "UPDATE member SET mem_id = :memId, mem_pw = :memPw, mem_name = :memName, mem_email = :memEmail, mem_phone = :memPhone, mem_sex = :memSex, mem_birth = :memBirth, mem_addr = :memAddr, mem_type = :memType WHERE mem_num = :memNum", nativeQuery = true)
    void updateMemberInfo(@Param("memNum") String memNum,
                        @Param("memId") String memId,
                        @Param("memPw") String memPw,
                        @Param("memName") String memName,
                        @Param("memEmail") String memEmail,
                        @Param("memPhone") String memPhone,
                        @Param("memSex") String memSex,
                        @Param("memBirth") LocalDate memBirth,
                        @Param("memAddr") String memAddr,
                        @Param("memType") int memType);

        //Toss
        @Query(value = "SELECT mem_birth FROM member  WHERE mem_num = :memNum", nativeQuery = true)
        Date findMemBirthByMemNum(@Param("memNum") String memNum);
}