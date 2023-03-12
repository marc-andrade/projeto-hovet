package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.RoleDTO;
import com.ufra.projetohovet.dto.UserDTO;
import com.ufra.projetohovet.entities.Role;
import com.ufra.projetohovet.entities.User;
import com.ufra.projetohovet.repositories.RoleRepository;
import com.ufra.projetohovet.repositories.SetorRepository;
import com.ufra.projetohovet.repositories.UserRepository;
import com.ufra.projetohovet.services.exceptions.DatabaseException;
import com.ufra.projetohovet.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final SetorRepository setorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO findById(Long id) {
        return new UserDTO(
                repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }

    @Transactional
    public UserDTO insert(UserDTO entity) {
        try {
            User obj = new User();
            copyDtoToEntity(entity, obj);
            obj.setPassword(passwordEncoder.encode(entity.getPassword()));
            return new UserDTO(repository.save(obj));

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Email já cadastrado");
        }

    }

    @Transactional
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);

        return list.map(UserDTO::new);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {

        User oldUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID não existe no banco de dados"));

        if (!dto.getPassword().equals(oldUser.getPassword())) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        checkExistsEmail(id, dto);
        copyDtoToEntity(dto, oldUser);

        return new UserDTO(repository.save(oldUser));

    }

    @Transactional
    public void delete(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDTO entity, User obj) {

        obj.setFullName(entity.getName());
        obj.setEmail(entity.getEmail());
        obj.setPassword(entity.getPassword());

        if (entity.getRoles().isEmpty() && obj.getRoles().isEmpty()) {
            entity.getRoles().add(new RoleDTO(2L, "ROLE_USER"));
        }
        if (entity.getRoles().size() > 0) {
            obj.getRoles().clear();

            entity.getRoles().forEach(dto -> {
                Role role = roleRepository.getReferenceById(dto.getId());
                obj.getRoles().add(role);
            });
        }

        if (entity.getSetor() != null) {
            obj.setSetor(setorRepository.getReferenceById(entity.getSetor().getId()));
        }
    }

    private void checkExistsEmail(Long id, UserDTO dto) {
        Optional<User> obj = repository.findByEmail(dto.getEmail());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), id)) {
            throw new DatabaseException("E-mail já cadastrado no sistema!");
        }
    }

}
