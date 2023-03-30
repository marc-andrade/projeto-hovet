package com.ufra.projetohovet.dto;

import com.ufra.projetohovet.enums.TipoInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsumoDTO {

    private Long id;
    private TipoInsumo tipo;
    private String nome;
    private Integer unidade;
    private CategoriaDTO Categoria;

}
