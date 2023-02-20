package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

}
