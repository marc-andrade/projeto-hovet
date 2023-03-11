package com.ufra.projetohovet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String nome;

}
