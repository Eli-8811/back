package com.core.back.service;

import com.core.back.dto.UserDTO;
import com.core.back.entity.UserEntity;
import com.core.back.exception.BusinessException;
import com.core.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor // ASPECTO: Patrón de Diseño (Inyección de dependencias por constructor)
public class UserService {

    private final UserRepository userRepository;
    
    private UserDTO toDto(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .email(entity.getEmail())
                .age(entity.getAge())
                .gender(entity.getGender())
                .status(entity.getStatus())
                .build();
    }

    private UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName().trim());
        entity.setLastname(dto.getLastname().trim());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        return entity;
    }
    
    // ASPECTO: Checked Exception (Exception es de tipo Checked)
    @Transactional
    public UserDTO saveUser(UserDTO userDto) {
        log.info("Iniciando persistencia para: {}", userDto.getEmail());        
        // Validación de negocio: Lanza Unchecked Exception si falla
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }
        UserEntity entity = toEntity(userDto);
        UserEntity saved = userRepository.save(entity);        
        log.info("Usuario guardado exitosamente: {}", saved.getEmail());
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getActiveUsers() {
        log.info("Consultando lista de usuarios activos...");
        return userRepository.findByStatus(true)
        		// ASPECTO: Programación Funcional (Optional y Stream API)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BusinessException("No se encontraron usuarios activos"))
                .stream()// ASPECTO: Stream API (Abre el flujo de datos)
                .map(this::toDto) // ASPECTO: Expresión Lambda (Method Reference - Programación funcional)
                .collect(Collectors.toList()); // ASPECTO: Colecciones (List)
    }
    
    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {
        log.info("Buscando usuario por email: {}", email);
        return userRepository.findByEmail(email).map(this::toDto).orElseThrow(() -> new BusinessException("No existe usuario con el email: " + email));
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO dto) {
        log.info("Actualizando datos del usuario ID: {}", id);        
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new BusinessException("El usuario a actualizar no existe"));
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("El nuevo email ya está en uso por otro perfil");
        }
        user.setName(dto.getName().trim());
        user.setLastname(dto.getLastname().trim());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());        
        return toDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("Solicitud de eliminación para ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new BusinessException("No se puede eliminar: El usuario no existe");
        }
        userRepository.deleteById(id);
    }
    
}