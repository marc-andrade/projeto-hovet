package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

}
