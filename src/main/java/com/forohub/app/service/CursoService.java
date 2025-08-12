package com.forohub.app.service;

import com.forohub.app.domain.Curso;
import com.forohub.app.domain.repo.CursoRepository;
import com.forohub.app.web.dto.CursoCrear;
import com.forohub.app.web.dto.CursoEditar;
import com.forohub.app.web.dto.CursoRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {
    private final CursoRepository cursos;

    public CursoService(CursoRepository cursos) {
        this.cursos = cursos;
    }

    @Transactional
    public CursoRespuesta crear(CursoCrear dto) {
        Curso c = Curso.builder().nombre(dto.nombre()).categoria(dto.categoria()).build();
        c = cursos.save(c);
        return new CursoRespuesta(c.getId(), c.getNombre(), c.getCategoria());
    }

    @Transactional(readOnly = true)
    public Page<CursoRespuesta> listar(Pageable pageable) {
        return cursos.findAll(pageable).map(c -> new CursoRespuesta(c.getId(), c.getNombre(), c.getCategoria()));
    }

    @Transactional(readOnly = true)
    public CursoRespuesta porId(Long id) {
        Curso c = cursos.findById(id).orElseThrow();
        return new CursoRespuesta(c.getId(), c.getNombre(), c.getCategoria());
    }

    @Transactional
    public CursoRespuesta editar(Long id, CursoEditar dto) {
        Curso c = cursos.findById(id).orElseThrow();
        if (dto.nombre() != null) c.setNombre(dto.nombre());
        if (dto.categoria() != null) c.setCategoria(dto.categoria());
        return new CursoRespuesta(c.getId(), c.getNombre(), c.getCategoria());
    }

    @Transactional
    public void eliminar(Long id) {
        cursos.deleteById(id);
    }
}
