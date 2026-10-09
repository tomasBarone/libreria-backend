CREATE TABLE biblioteca_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    libro_id BIGINT NOT NULL,
    fecha_guardado DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_biblioteca_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_biblioteca_libro FOREIGN KEY (libro_id) REFERENCES libro(id) ON DELETE CASCADE,
    CONSTRAINT uq_usuario_libro UNIQUE (usuario_id, libro_id)
);