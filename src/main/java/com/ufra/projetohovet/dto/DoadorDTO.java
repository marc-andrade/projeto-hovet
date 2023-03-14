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
public class DoadorDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String nome;
    @NotBlank(message = "campo obrigatório")
    private String descricao;


}
