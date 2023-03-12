package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.CategoriaDTO;
import com.ufra.projetohovet.entities.Categoria;
import com.ufra.projetohovet.repositories.CategoriaRepository;
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
public class CategoriaService {

    private final CategoriaRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public CategoriaDTO findById(Long id) {

        Categoria entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return mapper.map(entity, CategoriaDTO.class);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO entity) {

        return mapper.map(
                repository.save(mapper.map(entity, Categoria.class))
                , CategoriaDTO.class);
    }

    @Transactional
    public Page<CategoriaDTO> findAll(Pageable pageable) {
        Page<Categoria> page = repository.findAll(pageable);
        return page.map(x -> mapper.map(x, CategoriaDTO.class));
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria oldEntity = repository.getReferenceById(id);
            copyDtoToEntity(dto, oldEntity);

            return mapper.map(repository.save(oldEntity), CategoriaDTO.class);

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

    private void copyDtoToEntity(CategoriaDTO dto, Categoria oldEntity) {
        oldEntity.setNome(dto.getNome());
    }

}
