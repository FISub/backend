package com.woorifisa.backend.member.exception;

public class NotValidPasswordException extends Exception{
    public NotValidPasswordException() {}
	public NotValidPasswordException(String message) {
		super(message);
	}
}
