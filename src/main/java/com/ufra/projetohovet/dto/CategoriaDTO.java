package com.ufra.projetohovet.dto;

import com.ufra.projetohovet.entities.Setor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    private String nome;

    public CategoriaDTO(Setor setor){
        this.id =  setor.getId();
        this.nome = setor.getSigla();
    }

}
