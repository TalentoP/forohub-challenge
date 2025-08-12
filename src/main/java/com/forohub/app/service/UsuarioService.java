package com.forohub.app.service;

import com.forohub.app.domain.Rol;
import com.forohub.app.domain.Usuario;
import com.forohub.app.domain.repo.UsuarioRepository;
import com.forohub.app.web.dto.UsuarioCrear;
import com.forohub.app.web.dto.UsuarioRespuesta;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarios;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarios, PasswordEncoder encoder) {
        this.usuarios = usuarios;
        this.encoder = encoder;
    }

    @Transactional
    public UsuarioRespuesta crear(UsuarioCrear dto) {
        Usuario u = Usuario.builder().nombre(dto.nombre()).email(dto.email()).password(encoder.encode(dto.password())).rol(Rol.USUARIO).build();
        u = usuarios.save(u);
        return new UsuarioRespuesta(u.getId(), u.getNombre(), u.getEmail(), u.getRol().name());
    }
}
