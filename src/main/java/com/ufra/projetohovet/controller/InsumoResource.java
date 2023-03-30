package com.ufra.projetohovet.controller;


import com.ufra.projetohovet.dto.InsumoDTO;
import com.ufra.projetohovet.services.InsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/insumos")
@RequiredArgsConstructor
public class InsumoResource {

    private final InsumoService service;

    @GetMapping
    public ResponseEntity<List<InsumoDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<InsumoDTO> insert(@Valid @RequestBody InsumoDTO dto){
        InsumoDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsumoDTO> update(@PathVariable Long id, @Valid @RequestBody InsumoDTO dto){
        return ResponseEntity.ok().body(service.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
