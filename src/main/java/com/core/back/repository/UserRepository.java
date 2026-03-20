package com.core.back.repository;

import com.core.back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

//ASPECTO: Interface (Define un contrato de comportamiento)
//ASPECTO: Generics (JpaRepository<T, ID> donde T es UserEntity e ID es Long)
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	// ASPECTO: Polimorfismo (Spring implementa esta interfaz en tiempo de ejecución de diferentes formas)
    Optional<UserEntity> findByEmail(String email);

    Optional<List<UserEntity>> findByStatus(Boolean status);

    boolean existsByEmail(String email);
    
}