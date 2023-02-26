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
@Table(name = "tb_movimentacao")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @OneToOne
    private SolicitacaoDeCompra solicitacaoDeCompra;
    @OneToOne
    private NotaFiscal notaFiscal;
}
