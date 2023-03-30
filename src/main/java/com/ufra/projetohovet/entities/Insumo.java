package com.ufra.projetohovet.entities;

import com.ufra.projetohovet.enums.TipoInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_insumo")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoInsumo tipo;
    private String nome;
    private Integer unidade;
    @ManyToOne
    private Categoria Categoria;
    @OneToMany(mappedBy = "insumo")
    private List<Estoque> estoques;
    @OneToMany(mappedBy = "insumo")
    private List<ItemSolicitado> itemsSolicitados;

}
