package com.ufra.projetohovet.entities;

import com.ufra.projetohovet.enums.EstoqueLocal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private EstoqueLocal local;
    private Integer quantidade;
    private LocalDate validade;
    private Integer unidade;
    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;
}
