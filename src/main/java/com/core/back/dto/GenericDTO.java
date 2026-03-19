package com.core.back.dto;

public class GenericDTO<T> {
	
    private boolean success;
    private String message;
    private T data;

    public GenericDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> GenericDTO<T> ok(String message, T data) {
        return new GenericDTO<>(true, message, data);
    }

    public static <T> GenericDTO<T> error(String message) {
        return new GenericDTO<>(false, message, null);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    
}