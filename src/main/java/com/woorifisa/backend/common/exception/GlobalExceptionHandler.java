package com.woorifisa.backend.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.woorifisa.backend.member.exception.JoinException;
import com.woorifisa.backend.member.exception.NotValidPasswordException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotValidPasswordException.class)
    public ResponseEntity<Map<String, String>> handleNotValidPasswordException(NotValidPasswordException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage()); // 예외 메시지
        return ResponseEntity.badRequest().body(response); // 400 상태 코드
    }

    @ExceptionHandler(SessionNotValidException.class)
    public ResponseEntity<Map<String, String>> SessionNotValidException(SessionNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage()); // 예외 메시지
        return ResponseEntity.badRequest().body(response); // 400 상태 코드
    }

    @ExceptionHandler(JoinException.class)
    public ResponseEntity<Map<String, String>> JoinException(JoinException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage()); // 예외 메시지
        return ResponseEntity.badRequest().body(response); // 400 상태 코드
    }
}
