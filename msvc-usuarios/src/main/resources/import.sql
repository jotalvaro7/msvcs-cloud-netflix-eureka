INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('julio_osorio', '12345', TRUE, 'Julio', 'Osorio', 'julio@mail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('luisa_severino', '12345', TRUE, 'Luisa', 'Severino', 'luisa@mail.com');


INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
