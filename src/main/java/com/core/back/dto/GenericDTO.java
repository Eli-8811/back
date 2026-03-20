package com.core.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//ASPECTO: Generics (El uso de <T> permite que esta clase transporte cualquier tipo de objeto)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericDTO<T> {
	
    private boolean success; 
    private String message;
    
    private T data; // ASPECTO: Atributo Genérico

    // ASPECTO: Método Estático y Genérico
    public static <T> GenericDTO<T> ok(String message, T data) {
        return new GenericDTO<>(true, message, data);
    }

    // ASPECTO: Método Estático y Genérico
    public static <T> GenericDTO<T> error(String message) {
        return new GenericDTO<>(false, message, null);
    }
    
}