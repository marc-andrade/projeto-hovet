package com.ufra.projetohovet.controller;


import com.ufra.projetohovet.dto.RoleDTO;
import com.ufra.projetohovet.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleResource {

    private final RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

}
