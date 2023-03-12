//package com.ufra.projetohovet.controller;
//
//
//import com.ufra.projetohovet.dto.SetorDTO;
//import com.ufra.projetohovet.services.SetorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.validation.Valid;
//import java.net.URI;
//
//@RestController
//@RequestMapping("/setores")
//@RequiredArgsConstructor
//public class CategoriaResource {
//
//    private final SetorService service;
//
//    @GetMapping
//    public ResponseEntity<Page<SetorDTO>> findAll(Pageable pageable){
//        return ResponseEntity.ok().body(service.findAll(pageable));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SetorDTO> findById(@PathVariable Long id){
//        return ResponseEntity.ok().body(service.findById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<SetorDTO> insert(@Valid @RequestBody SetorDTO dto){
//        SetorDTO newDto = service.insert(dto);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
//                .buildAndExpand(newDto.getId()).toUri();
//        return ResponseEntity.created(uri).body(newDto);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<SetorDTO> update(@PathVariable Long id, @Valid @RequestBody SetorDTO dto){
//        return ResponseEntity.ok().body(service.update(id,dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//
//}
