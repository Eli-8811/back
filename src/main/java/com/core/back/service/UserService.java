package com.core.back.service;

import com.core.back.entity.UserEntity;
import com.core.back.exception.BusinessException;
import com.core.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity saveUser(UserEntity user) {
        // Validamos el email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BusinessException("email", "El email ya está registrado");
        }
        
        // Aquí podrías validar el teléfono si quisieras
        // if (userRepository.existsByPhone(user.getPhone())) {
        //     throw new BusinessException("phone", "El teléfono ya existe");
        // }
        
        return userRepository.save(user);
    }
    
}