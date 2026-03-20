package com.core.back.controller;

import com.core.back.dto.GenericDTO;
import com.core.back.dto.UserDTO;
import com.core.back.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController // ASPECTO: Patrón de Diseño (Front Controller / MVC)
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericDTO<UserDTO>> createUser(@Valid @RequestBody UserDTO userDto) throws Exception {
        log.info("REST request para crear un usuario: {}", userDto.getEmail());
        UserDTO saved = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericDTO.ok("Usuario creado exitosamente", saved));
    }
    
    @GetMapping
    public ResponseEntity<GenericDTO<List<UserDTO>>> getAllActiveUsers() {
    	// ASPECTO: Colecciones (Uso de List para agrupar objetos UserDTO)
        log.info("REST request para obtener lista completa de usuarios activos");
        List<UserDTO> activeUsers = userService.getActiveUsers();
        return ResponseEntity.ok(GenericDTO.ok("Lista de usuarios activos recuperada", activeUsers));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GenericDTO<UserDTO>> getUserByEmail(@PathVariable String email) {
        log.info("REST request para obtener usuario por email: {}", email);
        UserDTO user = userService.findUserByEmail(email);
        return ResponseEntity.ok(GenericDTO.ok("Usuario encontrado exitosamente", user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericDTO<UserDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDto) {
        log.info("REST request para actualizar usuario ID: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(GenericDTO.ok("Usuario actualizado exitosamente", updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericDTO<Void>> deleteUser(@PathVariable Long id) {
        log.info("REST request para eliminar usuario ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(GenericDTO.ok("Usuario eliminado exitosamente", null));
    }
    
}