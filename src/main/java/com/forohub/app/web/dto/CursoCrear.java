package com.forohub.app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CursoCrear(@NotBlank @Size(max = 160) String nombre, @NotBlank @Size(max = 120) String categoria) {
}
