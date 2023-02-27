package com.ufra.projetohovet.services;

import com.ufra.projetohovet.entities.Role;
import com.ufra.projetohovet.entities.User;
import com.ufra.projetohovet.repositories.RoleRepository;
import com.ufra.projetohovet.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void instanciaDB(){

        Role role1 = new Role(null,"ROLE_ADMIN");
        Role role2 = new Role(null, "ROLE_USER");

        User user1 = new User(null,
                "Marcos Andrade",
                "marcos@email.com",
                passwordEncoder.encode("123"), List.of(role1,role2));
        User user4 = new User(null,
                "admin",
                "admin@email.com",
                passwordEncoder.encode("123"), List.of(role1));
        User user2 = new User(null,
                "User",
                "user@email.com",
                passwordEncoder.encode("123"), List.of(role2));

        roleRepository.saveAll(List.of(role1,role2));
        userRepository.saveAll(List.of(user1,user2,user4));

    }
}
