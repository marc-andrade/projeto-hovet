package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.ItemDaNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDaNotaRepository extends JpaRepository<ItemDaNota, Long> {

}
