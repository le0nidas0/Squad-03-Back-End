package com.example.squad03.controller;

import com.example.squad03.dto.UserCreateDTO;
import com.example.squad03.dto.UserProfileResponse;
import com.example.squad03.dto.UserProfileUpdateRequest;
import com.example.squad03.dto.UserResponseDTO;
import com.example.squad03.model.Role;
import com.example.squad03.model.Usuario;
import com.example.squad03.repository.UsuarioRepository;
import com.example.squad03.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name="Usuários", description="CRUD de usuários (Admin only)")
public class UserController {

    private final UserService userService;
    private final UsuarioRepository userRepo;

    public UserController(UserService svc, UsuarioRepository repo) {
        this.userService = svc;
        this.userRepo = repo;
    }

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários do sistema. Apenas administradores podem acessar este endpoint.")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDTO> listAll() {
        return userService.findAll().stream()
                .map(u -> new UserResponseDTO(
                        u.getId(), u.getEmail(), u.getNome(),
                        u.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
                )).toList();
    }

    @Operation(summary = "Busca usuário por ID", description = "Retorna os detalhes de um usuário específico pelo ID. Apenas administradores podem acessar este endpoint.")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO getById(@PathVariable Long id) {
        Usuario u = userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserResponseDTO(
                u.getId(), u.getEmail(), u.getNome(),
                u.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário no sistema. Apenas administradores podem acessar este endpoint.")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO create(@RequestBody UserCreateDTO dto) {
        Usuario u = userService.create(dto);
        return new UserResponseDTO(u.getId(), u.getEmail(), u.getNome(),
                u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

    @Operation(summary = "Atualiza um usuário existente", description = "Atualiza os detalhes de um usuário existente. Apenas administradores podem acessar este endpoint.")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserCreateDTO dto) {
        Usuario u = userService.update(id, dto);
        return new UserResponseDTO(u.getId(), u.getEmail(), u.getNome(),
                u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

    @Operation(summary = "Deleta um usuário", description = "Remove um usuário do sistema pelo ID. Apenas administradores podem acessar este endpoint.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adiciona uma role a um usuário", description = "Atribui uma nova role a um usuário existente. Apenas administradores podem acessar este endpoint.")
    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO addRole(@PathVariable Long id, @RequestParam String role) {
        Usuario u = userService.assignRole(id, role);
        return new UserResponseDTO(u.getId(), u.getEmail(), u.getNome(),
                u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    }

    @Operation(summary = "Obtém o perfil do usuário autenticado", description = "Retorna os detalhes do perfil do usuário autenticado.")
    @GetMapping("/me")
    public UserProfileResponse getProfile(Authentication auth) {
        String email = auth.getName();

        Usuario u = userService.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Set<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return new UserProfileResponse(u.getId(), u.getEmail(), u.getNome(), roles);
    }

    @Operation(summary = "Atualiza o perfil do usuário autenticado", description = "Permite que o usuário autenticado atualize seu perfil.")
    @PutMapping("/me")
    public UserProfileResponse updateProfile(
            Authentication auth,
            @RequestBody UserProfileUpdateRequest req) {
        String email = auth.getName();
        return userService.updateProfile(email, req);
    }

    @Operation(summary = "Remove uma role de um usuário", description = "Remove uma role específica de um usuário. Apenas administradores podem acessar este endpoint.")
    @DeleteMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO removeRole(
            @PathVariable("id") Long userId,
            @RequestParam("role") String roleName) {

        Usuario updated = userService.removeRole(userId, roleName);
        return new UserResponseDTO(
                updated.getId(),
                updated.getEmail(),
                updated.getNome(),
                updated.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}
