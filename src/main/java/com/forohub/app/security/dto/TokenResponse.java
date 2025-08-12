package com.forohub.app.security.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse(@NotBlank String token) {}
