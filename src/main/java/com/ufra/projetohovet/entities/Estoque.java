package com.ufra.projetohovet.entities;

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
    private Integer quantidade;
    private LocalDate validade;
    private Integer unidade;
    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;
    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;
}
