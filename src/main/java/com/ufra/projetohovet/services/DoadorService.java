package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.DoadorDTO;
import com.ufra.projetohovet.entities.Doador;
import com.ufra.projetohovet.repositories.DoadorRepository;
import com.ufra.projetohovet.services.exceptions.DatabaseException;
import com.ufra.projetohovet.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoadorService {

    private final DoadorRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public DoadorDTO findById(Long id) {

        Doador entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return mapper.map(entity, DoadorDTO.class);
    }

    @Transactional
    public DoadorDTO insert(DoadorDTO entity) {

        return mapper.map(
                repository.save(mapper.map(entity, Doador.class))
                , DoadorDTO.class);
    }

    @Transactional
    public List<DoadorDTO> findAll() {
        List<Doador> list = repository.findAll();
        return list.stream().map(x -> mapper.map(x, DoadorDTO.class)).toList();
    }

    @Transactional
    public DoadorDTO update(Long id, DoadorDTO dto) {
        try {
            Doador oldEntity = repository.getReferenceById(id);
            if (dto.getId() == null) {
                dto.setId(id);
            }
            mapper.map(dto, oldEntity);

            return mapper.map(repository.save(oldEntity), DoadorDTO.class);

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

}
