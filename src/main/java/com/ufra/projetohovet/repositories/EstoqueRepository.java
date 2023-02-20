package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
