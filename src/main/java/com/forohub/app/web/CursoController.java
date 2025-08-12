package com.forohub.app.web;

import com.forohub.app.service.CursoService;
import com.forohub.app.web.dto.CursoCrear;
import com.forohub.app.web.dto.CursoEditar;
import com.forohub.app.web.dto.CursoRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<CursoRespuesta> listar(@PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoRespuesta> porId(@PathVariable Long id) {
        return ResponseEntity.ok(service.porId(id));
    }

    @PostMapping
    public ResponseEntity<CursoRespuesta> crear(@RequestBody CursoCrear dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoRespuesta> editar(@PathVariable Long id, @RequestBody CursoEditar dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
