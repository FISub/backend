package com.woorifisa.backend.common.exception;

public class SessionNotValidException extends Exception {
    public SessionNotValidException() {}
	public SessionNotValidException(String message) {
		super(message);
	}
}
