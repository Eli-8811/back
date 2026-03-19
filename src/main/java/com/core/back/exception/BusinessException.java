package com.core.back.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
    private final String field;

    public BusinessException(String field, String message) {
        super(message);
        this.field = field;
    }
    
}