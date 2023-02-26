package com.ufra.projetohovet.dto;

import com.ufra.projetohovet.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String name;
    @Email(message = "Informe um email válido")
    private String email;
    @NotBlank(message = "Campo obrigatório")
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFullName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
