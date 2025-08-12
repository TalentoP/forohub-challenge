package com.forohub.app.web.dto;

import java.time.LocalDateTime;

public record RespuestaRespuesta(
        Long id, String mensaje, LocalDateTime fechaCreacion, Boolean solucion, Long autorId, Long topicoId
) {}
