package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.ItemSolicitado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSolicitadoRepository extends JpaRepository<ItemSolicitado, Long> {

}
