-- insert datos de prueba
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres', '12345', 1, 'Andres', 'Guzman', 'andres@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '12345', 1, 'Jhon', 'Doe', 'jhon.doe@email.com');


-- insert roles
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

-- insert usuarios_roles
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);
