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
public class RespuestaService {
    private final RespuestaRepository respuestas;
    private final TopicoRepository topicos;
    private final UsuarioRepository usuarios;

    public RespuestaService(RespuestaRepository respuestas, TopicoRepository topicos, UsuarioRepository usuarios) {
        this.respuestas = respuestas;
        this.topicos = topicos;
        this.usuarios = usuarios;
    }

    @Transactional
    public RespuestaRespuesta crear(Long topicoId, RespuestaCrear dto) {
        Topico topico = topicos.findById(topicoId).orElseThrow();
        Usuario autor = usuarios.findById(dto.autorId()).orElseThrow();
        Respuesta r = Respuesta.builder()
                .mensaje(dto.mensaje())
                .fechaCreacion(LocalDateTime.now())
                .solucion(Boolean.TRUE.equals(dto.solucion()))
                .autor(autor)
                .topico(topico)
                .build();
        r = respuestas.save(r);
        return new RespuestaRespuesta(r.getId(), r.getMensaje(), r.getFechaCreacion(), r.isSolucion(), r.getAutor().getId(), r.getTopico().getId());
    }

    @Transactional(readOnly = true)
    public Page<RespuestaRespuesta> listarPorTopico(Long topicoId, Pageable pageable) {
        return respuestas.findAll(pageable).map(r -> new RespuestaRespuesta(r.getId(), r.getMensaje(), r.getFechaCreacion(), r.isSolucion(), r.getAutor().getId(), r.getTopico().getId()));
    }
}
