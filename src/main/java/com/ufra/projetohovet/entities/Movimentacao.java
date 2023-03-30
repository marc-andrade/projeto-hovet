package com.ufra.projetohovet.entities;

import com.ufra.projetohovet.enums.TipoMovimentacao;
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
@Table(name = "tb_movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;
    @ManyToOne
    private Local origem;
    @ManyToOne
    private Local destino;
    private LocalDate cadastro;
    @ManyToOne
    private User user;
    @ManyToOne
    private Insumo insumo;
    @ManyToOne
    private Permuta permuta;
    @ManyToOne
    private Doacao doacao;
    @ManyToOne
    private Compra compra;

}
