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
@Table(name = "tb_movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String tipo;
    private String origem;
    private String destino;
    private LocalDate cadastro;

    @ManyToOne
    private User user;
    @ManyToOne
    private Estoque estoque;
    @ManyToOne
    private Dispensario dispensario;

}
