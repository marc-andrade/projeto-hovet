package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.SetorDTO;
import com.ufra.projetohovet.entities.Setor;
import com.ufra.projetohovet.repositories.SetorRepository;
import com.ufra.projetohovet.services.exceptions.DatabaseException;
import com.ufra.projetohovet.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SetorService {

    private final SetorRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public SetorDTO findById(Long id) {

        Setor entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return mapper.map(entity, SetorDTO.class);
    }

    @Transactional
    public SetorDTO insert(SetorDTO entity) {

        return mapper.map(
                repository.save(mapper.map(entity, Setor.class))
                , SetorDTO.class);
    }

    @Transactional
    public Page<SetorDTO> findAll(Pageable pageable) {
        Page<Setor> list = repository.findAll(pageable);
        return list.map(x -> mapper.map(x, SetorDTO.class));
    }

    @Transactional
    public SetorDTO update(Long id, SetorDTO dto) {
        try {
            Setor oldEntity = repository.getReferenceById(id);
            copyDtoToEntity(dto, oldEntity);

            return mapper.map(repository.save(oldEntity), SetorDTO.class);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(SetorDTO dto, Setor oldEntity) {
        oldEntity.setNome(dto.getNome());
    }

}
