package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Permuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermutaRepository extends JpaRepository<Permuta, Long> {

}
