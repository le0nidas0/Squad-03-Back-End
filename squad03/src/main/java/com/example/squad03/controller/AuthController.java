package com.example.squad03.controller;

import com.example.squad03.dto.AuthRequest;
import com.example.squad03.dto.AuthResponse;
import com.example.squad03.model.Usuario;
import com.example.squad03.repository.UsuarioRepository;
import com.example.squad03.service.impl.CustomUserDetailsService;
import com.example.squad03.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login de usuários") // Tag for the controller
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta de usuário com email e senha.")
    @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
            content = @Content(schema = @Schema(implementation = String.class))) // Successful response
    @ApiResponse(responseCode = "400", description = "Email já cadastrado",
            content = @Content(schema = @Schema(implementation = String.class))) // Bad request response
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Usuario user = new Usuario();
        user.setEmail(request.getEmail());
        user.setSenha(passwordEncoder.encode(request.getSenha()));
        usuarioRepository.save(user);

        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Autentica um usuário e retorna um token JWT.")
    @ApiResponse(responseCode = "200", description = "Login bem-sucedido, retorna token JWT",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))) // Successful response with AuthResponse schema
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas") // Unauthorized response
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}