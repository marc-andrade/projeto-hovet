package com.ufra.projetohovet.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_item_solicitado")
public class ItemSolicitado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    private Insumo insumo;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "solicitacao_de_compra_id")
    private SolicitacaoDeCompra solicitacaoDeCompra;

}
