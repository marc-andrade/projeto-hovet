package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.RoleDTO;
import com.ufra.projetohovet.entities.Setor;
import com.ufra.projetohovet.repositories.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final SetorRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public List<RoleDTO> findAll() {
        List<Setor> list = repository.findAll();
        return list.stream().map(x -> mapper.map(x, RoleDTO.class)).toList();
    }

}
