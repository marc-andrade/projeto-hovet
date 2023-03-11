package com.ufra.projetohovet.controller;


import com.ufra.projetohovet.dto.RoleDTO;
import com.ufra.projetohovet.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleResource {

    private final RoleService service;

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

}
