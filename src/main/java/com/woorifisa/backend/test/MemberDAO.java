package com.woorifisa.backend.test;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    private final JdbcTemplate jdbcTemplate;

    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String login(Map<String, String> reqMap) {
        String sql = "SELECT COUNT(*) FROM member WHERE mem_id = ? AND mem_pw = ?";
        String id = reqMap.get("id");
        String pw = reqMap.get("pw");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id, pw);
        return count > 0 ? "login success" : "존재하지 않는 아이디 입니다.";
    }
}

