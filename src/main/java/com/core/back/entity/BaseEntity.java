package com.core.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//ASPECTO: Clase Abstracta (No se puede instanciar, define una base común)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "status")
    private Boolean status = true; // ASPECTO: Encapsulamiento

    // ASPECTO: Métodos protegidos (Accesibles por herencia)
    @Column(name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }
    
}