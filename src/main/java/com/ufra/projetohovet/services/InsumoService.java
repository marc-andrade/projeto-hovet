package com.ufra.projetohovet.services;


import com.ufra.projetohovet.dto.InsumoDTO;
import com.ufra.projetohovet.entities.Insumo;
import com.ufra.projetohovet.repositories.CategoriaRepository;
import com.ufra.projetohovet.repositories.InsumoRepository;
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
public class InsumoService {

    private final InsumoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper mapper;

    @Transactional
    public InsumoDTO findById(Long id) {

        Insumo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not Found"));
        return mapper.map(entity, InsumoDTO.class);
    }

    @Transactional
    public InsumoDTO insert(InsumoDTO entity) {

        return mapper.map(
                repository.save(mapper.map(entity, Insumo.class))
                , InsumoDTO.class);
    }

    @Transactional
    public List<InsumoDTO> findAll() {
        List<Insumo> list = repository.findAll();
        return list.stream().map(x -> mapper.map(x, InsumoDTO.class)).toList();
    }

    @Transactional
    public InsumoDTO update(Long id, InsumoDTO dto) {
        try {
            Insumo oldEntity = repository.getReferenceById(id);
            copyDtoToEntity(dto, oldEntity);

            return mapper.map(repository.save(oldEntity), InsumoDTO.class);

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

    private void copyDtoToEntity(InsumoDTO dto, Insumo oldEntity) {

        oldEntity.setTipo(dto.getTipo());
        oldEntity.setNome(dto.getNome());
        oldEntity.setUnidade(dto.getUnidade());
        oldEntity.setCategoria(categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não existe")));
    }

}
