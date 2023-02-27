package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.SolicitacaoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoDeCompraRepository extends JpaRepository<SolicitacaoDeCompra, Long> {

}
