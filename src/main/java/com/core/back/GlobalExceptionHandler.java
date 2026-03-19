package com.core.back;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.core.back.dto.GenericDTO;
import com.core.back.exception.BusinessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Homologación para Validaciones (@NotBlank, @Valid, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericDTO<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        // Forzamos el mensaje principal para que sea igual en todos los errores de datos
        return ResponseEntity.badRequest()
                .body(new GenericDTO<>(false, "Error de validación en los datos", errors));
    }

    // 2. Homologación para Errores de Negocio (Email duplicado)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GenericDTO<Map<String, String>>> handleBusinessException(BusinessException ex) {
        Map<String, String> errorData = new HashMap<>();
        
        // Usamos el campo y mensaje que vienen de la excepción
        errorData.put(ex.getField(), ex.getMessage());

        // Usamos el mismo mensaje principal "Error de validación en los datos"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GenericDTO<>(false, "Error de validación en los datos", errorData));
    }
    
}