CREATE TABLE usuario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL,
  password VARCHAR(255) NOT NULL,
  rol VARCHAR(20) NOT NULL,
  UNIQUE KEY uk_usuario_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE curso (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(160) NOT NULL,
  categoria VARCHAR(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE topico (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(160) NOT NULL,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  estado VARCHAR(20) NOT NULL,
  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,
  INDEX idx_topico_fecha (fecha_creacion),
  INDEX idx_topico_estado (estado),
  CONSTRAINT fk_topico_autor FOREIGN KEY (autor_id) REFERENCES usuario(id),
  CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE respuesta (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  mensaje TEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  solucion BOOLEAN NOT NULL,
  autor_id BIGINT NOT NULL,
  topico_id BIGINT NOT NULL,
  INDEX idx_respuesta_fecha (fecha_creacion),
  CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor_id) REFERENCES usuario(id),
  CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topico(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
