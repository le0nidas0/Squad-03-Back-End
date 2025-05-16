package com.example.squad03.resource;

import com.example.squad03.dto.LoginRequest;
import com.example.squad03.model.User;
import com.example.squad03.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User novo = authService.register(user);
        return ResponseEntity.ok(novo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = authService.authenticate(request);
        if (success) {
            return ResponseEntity.ok("Login realizado com sucesso");
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }
    }
}
