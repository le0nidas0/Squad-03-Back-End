package com.example.squad03.service;

import com.example.squad03.dto.UserCreateDTO;
import com.example.squad03.dto.UserProfileResponse;
import com.example.squad03.dto.UserProfileUpdateRequest;
import com.example.squad03.model.Role;
import com.example.squad03.model.Usuario;
import com.example.squad03.repository.RoleRepository;
import com.example.squad03.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsuarioRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserService(UsuarioRepository userRepo, RoleRepository roleRepo, PasswordEncoder enc) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = enc;
    }

    public Optional<Usuario> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public Usuario save(Usuario u) {
        return userRepo.save(u);
    }

    public List<Usuario> findAll() { return userRepo.findAll(); }

    public Optional<Usuario> findById(Long id) { return userRepo.findById(id); }

    public Usuario create(UserCreateDTO dto) {
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setSenha(encoder.encode(dto.getSenha()));
        // atribuir roles padrão
        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER não encontrada"));
        u.getRoles().add(userRole);
        return userRepo.save(u);
    }

    public Usuario update(Long id, UserCreateDTO dto) {
        Usuario u = userRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não existe"));
        u.setEmail(dto.getEmail());
        u.setNome(dto.getNome());
        if (dto.getSenha()!=null) {
            u.setSenha(encoder.encode(dto.getSenha()));
        }
        // opcional: atualizar roles a partir de dto.getRoles()
        return userRepo.save(u);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public Usuario assignRole(Long userId, String roleName) {
        Usuario u = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não existe"));
        Role r = roleRepo.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Role não existe"));
        u.getRoles().add(r);
        return userRepo.save(u);
    }

    public UserProfileResponse updateProfile(String email, UserProfileUpdateRequest req) {
        Usuario u = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não existe"));

        u.setEmail(req.getEmail());
        u.setNome(req.getNome());
        if (req.getSenha() != null && !req.getSenha().isBlank()) {
            u.setSenha(encoder.encode(req.getSenha()));
        }

        Usuario salvo = userRepo.save(u);

        // Mapeando as roles para String
        Set<String> roles = salvo.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new UserProfileResponse(salvo.getId(), salvo.getEmail(), salvo.getNome(), roles);
    }

    public Usuario removeRole(Long userId, String roleName) {
        // 1) Busca o usuário
        Usuario u = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não existe"));

        // 2) Busca a role
        Role r = roleRepo.findByName(roleName)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role não existe"));

        // 3) Se não tiver, opcionalmente erro ou só segue
        if (!u.getRoles().remove(r)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuário não possui a role " + roleName);
        }

        // 4) Grava e retorna
        return userRepo.save(u);
    }
}