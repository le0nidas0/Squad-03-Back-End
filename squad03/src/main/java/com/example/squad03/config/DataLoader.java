package com.example.squad03.config;

import com.example.squad03.model.Role;
import com.example.squad03.model.Usuario;
import com.example.squad03.repository.RoleRepository;
import com.example.squad03.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final UsuarioRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public DataLoader(UsuarioRepository u,
                      RoleRepository r,
                      PasswordEncoder e) {
        this.userRepo = u;
        this.roleRepo = r;
        this.encoder = e;
    }

    @Override
    public void run(String... args) {
        // 1) garante que as roles existam no banco
        Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_ADMIN")));
        Role userRole = roleRepo.findByName("ROLE_USER")
                .orElseGet(() -> roleRepo.save(new Role(null, "ROLE_USER")));

        // 2) só cria o admin padrão se NÃO existir já
        //    qualquer usuário com ROLE_ADMIN
        boolean adminExists = userRepo.existsByRoles_Name("ROLE_ADMIN");
        if (!adminExists) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@admin.com");
            admin.setSenha(encoder.encode("admin123"));
            admin.getRoles().addAll(Set.of(adminRole, userRole));
            userRepo.save(admin);
            log.info("Criado usuário padrão admin@admin.com com ROLE_ADMIN");
        } else {
            log.info("Já existe usuário com ROLE_ADMIN, não cria padrão.");
        }
    }
}
