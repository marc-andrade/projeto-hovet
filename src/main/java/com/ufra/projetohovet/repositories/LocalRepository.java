package com.ufra.projetohovet.repositories;

import com.ufra.projetohovet.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

}
