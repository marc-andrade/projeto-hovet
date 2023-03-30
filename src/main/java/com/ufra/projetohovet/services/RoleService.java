package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.RoleDTO;
import com.ufra.projetohovet.entities.Setor;
import com.ufra.projetohovet.repositories.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final SetorRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Setor> page = repository.findAll(pageable);
        return page.map(x -> mapper.map(x, RoleDTO.class));
    }

}
