package com.core.back.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;
    
    private Integer age;
    private String gender;
    
    @Email(message = "Formato de email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    
    private Boolean status;
    
}