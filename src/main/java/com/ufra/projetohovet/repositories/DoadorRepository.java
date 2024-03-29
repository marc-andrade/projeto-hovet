package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {

}
