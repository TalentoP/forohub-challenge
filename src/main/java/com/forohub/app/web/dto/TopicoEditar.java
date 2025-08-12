package com.forohub.app.web.dto;

import jakarta.validation.constraints.Size;

public record TopicoEditar(
        @Size(max=160) String titulo,
        String mensaje
) {}
