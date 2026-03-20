package com.core.back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

//ASPECTO: Clases y Objetos (Definición de una Clase Entidad)
//ASPECTO: Herencia (UserEntity HEREDA de BaseEntity)
@Entity
@Table(name = "users")
@Getter 
@Setter
@NoArgsConstructor // ASPECTO: Método (Constructor por defecto)
@AllArgsConstructor
@Builder // ASPECTO: Patrón de Diseño (Builder Pattern para creación de objetos)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no debe ir vacío")
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastname;

    private Integer age;

    @Column(length = 20)
    private String gender;

    @NotBlank(message = "El email no debe ir vacío")
    @Email(message = "Debe proporcionar un formato de email válido")
    @Column(unique = true, nullable = false)
    private String email;

}