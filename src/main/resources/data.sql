-- Contraseña: Admin1
insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test)
values (10, 'Admin admin', 'admin@prueba.net', '5487787889S', 'admin',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0);

insert into usuario_roles (usuario_id, roles)
values (10, 'ADMIN');


insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (9, 'Pedro Rodriguez', 'pedro@profesor.com', '98474856W', 'profe1',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje, Matematicas');

insert into usuario_roles (usuario_id, roles)
values (9, 'TEACH');


insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (11, 'Benito Alvárez', 'benito@alumno.com', '76473847F', 'ben10',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje, Matematicas');

insert into usuario_roles (usuario_id, roles)
values (11, 'STUDE');

-- insert into tareas(id, descripcion, titulo, usuario_id)
-- values (2, 'Ejercicio lectura', 'Realiza este ejercicio', 3);

insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (12, 'Ruben Pérez', 'ruben@alumno.com', '23268765H', 'ruben12',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Ciencias Sociales, Geografía');

insert into usuario_roles (usuario_id, roles)
values (12, 'STUDE');


-- insert into tareas(id, descripcion, titulo, usuario_id)
-- values (1, 'Ejercicio repaso', 'Realiza este ejercicio', 4);
--
insert into tareas
values (1, 'Desarrolla un comentario de texto ', 'Realiza este ejercicio', 11)



