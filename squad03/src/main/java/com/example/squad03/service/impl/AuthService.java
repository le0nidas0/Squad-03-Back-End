package com.example.squad03.service.impl;

import com.example.squad03.dto.LoginRequest;
import com.example.squad03.model.User;
import com.example.squad03.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    public boolean authenticate(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        return optionalUser
                .map(user -> passwordEncoder.matches(request.getSenha(), user.getSenha()))
                .orElse(false);
    }
}
