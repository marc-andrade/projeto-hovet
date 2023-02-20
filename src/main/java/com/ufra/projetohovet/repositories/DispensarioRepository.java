package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Dispensario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispensarioRepository extends JpaRepository<Dispensario, Long> {

}
