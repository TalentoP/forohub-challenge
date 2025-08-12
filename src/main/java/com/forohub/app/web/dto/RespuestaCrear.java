package com.forohub.app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaCrear(
        @NotBlank String mensaje,
        @NotNull Long autorId,
        @NotNull Boolean solucion
) {}
