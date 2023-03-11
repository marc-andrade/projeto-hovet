package com.ufra.projetohovet.dto;

import com.ufra.projetohovet.entities.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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

}
