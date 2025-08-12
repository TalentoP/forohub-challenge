package com.forohub.app.web;

import com.forohub.app.service.RespuestaService;
import com.forohub.app.web.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos/{topicoId}/respuestas")
public class RespuestaController {
    private final RespuestaService service;

    public RespuestaController(RespuestaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<RespuestaRespuesta> listar(@PathVariable Long topicoId, @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {
        return service.listarPorTopico(topicoId, pageable);
    }

    @PostMapping
    public ResponseEntity<RespuestaRespuesta> crear(@PathVariable Long topicoId, @RequestBody RespuestaCrear dto) {
        return ResponseEntity.ok(service.crear(topicoId, dto));
    }
}
