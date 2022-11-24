-- Contraseña: Admin1
insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test)
values (10, 'Admin admin', 'admin@prueba.net', '5487787889S', 'admin',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0);

insert into usuario_roles (usuario_id, roles)
values (10, 'ADMIN');
insert into usuario_roles (usuario_id, roles)
values (10, 'TEACH');
insert into usuario_roles (usuario_id, roles)
values (10, 'STUDE');

insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note, dni, subjects)
values (5, 'Alberto', 'Aguilera', 'albert10', 'alberto@hotmail.com', 1234, CURRENT_TIMESTAMP, true, 0, '8765676G',
        'Lengua');
insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note, dni, subjects)
values (15, 'Javier', 'Pérez', 'javito', 'javier@hotmail.com', 1234, CURRENT_TIMESTAMP, false, 0, '3456432S', 'Lengua');
insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note, dni, subjects)
values (6, 'Andrés', 'Rodriguez', 'andrew', 'andres@hotmail.com', 1234, CURRENT_TIMESTAMP, true, 0, '6545676543D',
        'Lengua');

insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (4, 'Pedro Rodriguez', 'pedro@profesor.com', '98474856W', 'profe1',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje, Matematicas');

insert into usuario_roles (usuario_id, roles)
values (4, 'TEACH');

insert into teacher (id, name, surname, username, email, password, entry_date)
values (6, 'Pedro', 'Rodriguez', 'profe1', 'pedro@profesor.com',
        '$2a$10$vPaqZvZkz6jhb7U7kV/v5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2', CURRENT_TIMESTAMP);

insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (7, 'Alberto Aguilera', 'alberto@hotmail.com', '8765676G', 'albert10',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje');
insert into usuario_roles (usuario_id, roles)
values (7, 'STUDE');

insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (8, 'Javier Pérez', 'javier@hotmail.com', '3456432S', 'javito',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje');
insert into usuario_roles (usuario_id, roles)
values (8, 'STUDE');

insert into usuarios (id, full_name, email, dni, username, password, avatar, created_at,
                      last_password_change_at, entry_date, is_terminated, note, test, subjects)
values (9, 'Andrés Rodriguez', 'andres@hotmail.com', '6545676543D', 'andrew',
        '$2a$10$vPaqZvZkz6jhb7U7k/V/v.5vprfNdOnh4sxi/qpPRkYTzPmFlI9p2',
        'https://api.lorem.space/image/face?w=150&h=150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true,
        1, 0, 'Lenguaje');
insert into usuario_roles (usuario_id, roles)
values (9, 'STUDE');