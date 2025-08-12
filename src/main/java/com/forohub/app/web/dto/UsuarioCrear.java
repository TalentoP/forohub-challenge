package com.forohub.app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCrear(
        @NotBlank @Size(max=120) String nombre,
        @NotBlank @Email @Size(max=160) String email,
        @NotBlank @Size(min=6, max=100) String password
) {}
