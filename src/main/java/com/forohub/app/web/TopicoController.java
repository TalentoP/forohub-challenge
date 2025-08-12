package com.forohub.app.web;

import com.forohub.app.domain.EstadoTopico;
import com.forohub.app.domain.Topico;
import com.forohub.app.domain.repo.TopicoRepository;
import com.forohub.app.service.TopicoService;
import com.forohub.app.web.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TopicoRespuesta> listar(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoRespuesta> porId(@PathVariable Long id) {
        return ResponseEntity.ok(service.porId(id));
    }

    @PostMapping
    public ResponseEntity<TopicoRespuesta> crear(@RequestBody TopicoCrear dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoRespuesta> editar(@PathVariable Long id, @RequestBody TopicoEditar dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
