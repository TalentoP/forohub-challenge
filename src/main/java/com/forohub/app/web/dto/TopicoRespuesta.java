package com.forohub.app.web.dto;

import java.time.LocalDateTime;

public record TopicoRespuesta(
        Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String estado, Long autorId, Long cursoId
) {}
