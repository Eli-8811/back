package com.core.back.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.core.back.dto.GenericDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericDTO<Object>> handleValidation(MethodArgumentNotValidException ex) {
        log.error("Error de validación detectado: {}", ex.getBindingResult().getFieldError().getDefaultMessage());        
        return ResponseEntity.badRequest()
                .body(new GenericDTO<>(false, "Datos de entrada inválidos", null));
    }

    // ASPECTO: Polimorfismo (El manejador captura diferentes tipos de excepciones)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GenericDTO<Object>> handleBusinessException(BusinessException ex) {
        log.error("Error de negocio: {}", ex.getMessage());
        // ASPECTO: Manejo de Excepciones Unchecked
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GenericDTO<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericDTO<Object>> handleAllExceptions(Exception ex) {
        log.error("Error interno no controlado: ", ex);        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericDTO<>(false, "Ocurrió un error interno en el servidor", null));
    }
    
}