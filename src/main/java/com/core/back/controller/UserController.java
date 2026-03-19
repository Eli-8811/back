package com.core.back.controller;

import com.core.back.dto.GenericDTO;
import com.core.back.entity.UserEntity;
import com.core.back.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericDTO<UserEntity>> createUser(@Valid @RequestBody UserEntity user) {
    	
        UserEntity savedUser = userService.saveUser(user);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericDTO.ok("Usuario creado exitosamente", savedUser));
    }
    
}