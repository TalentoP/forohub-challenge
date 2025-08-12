package com.forohub.app.web;

import com.forohub.app.service.UsuarioService;
import com.forohub.app.web.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioRespuesta> crear(@RequestBody UsuarioCrear dto) {
        return ResponseEntity.ok(service.crear(dto));
    }
}
