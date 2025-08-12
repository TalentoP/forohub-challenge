package com.forohub.app.service;

import com.forohub.app.domain.*;
import com.forohub.app.domain.repo.*;
import com.forohub.app.web.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class TopicoService {
    private final TopicoRepository topicos;
    private final UsuarioRepository usuarios;
    private final CursoRepository cursos;

    public TopicoService(TopicoRepository topicos, UsuarioRepository usuarios, CursoRepository cursos) {
        this.topicos = topicos;
        this.usuarios = usuarios;
        this.cursos = cursos;
    }

    @Transactional
    public TopicoRespuesta crear(TopicoCrear dto) {
        Usuario autor = usuarios.findById(dto.autorId()).orElseThrow();
        Curso curso = cursos.findById(dto.cursoId()).orElseThrow();
        Topico t = Topico.builder()
                .titulo(dto.titulo())
                .mensaje(dto.mensaje())
                .fechaCreacion(LocalDateTime.now())
                .estado(EstadoTopico.ABIERTO)
                .autor(autor)
                .curso(curso)
                .build();
        t = topicos.save(t);
        return new TopicoRespuesta(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getEstado().name(), t.getAutor().getId(), t.getCurso().getId());
    }

    @Transactional(readOnly = true)
    public Page<TopicoRespuesta> listar(Pageable pageable) {
        return topicos.findAll(pageable).map(t -> new TopicoRespuesta(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getEstado().name(), t.getAutor().getId(), t.getCurso().getId()));
    }

    @Transactional(readOnly = true)
    public TopicoRespuesta porId(Long id) {
        Topico t = topicos.findById(id).orElseThrow();
        return new TopicoRespuesta(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getEstado().name(), t.getAutor().getId(), t.getCurso().getId());
    }

    @Transactional
    public TopicoRespuesta editar(Long id, TopicoEditar dto) {
        Topico t = topicos.findById(id).orElseThrow();
        if (dto.titulo() != null) t.setTitulo(dto.titulo());
        if (dto.mensaje() != null) t.setMensaje(dto.mensaje());
        return new TopicoRespuesta(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getEstado().name(), t.getAutor().getId(), t.getCurso().getId());
    }

    @Transactional
    public void eliminar(Long id) {
        topicos.deleteById(id);
    }
}
