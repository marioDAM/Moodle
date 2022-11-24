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

insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note)
values (5, 'Alberto', 'Aguilera', 'albert10', 'alberto@hotmail.com', 1234, CURRENT_TIMESTAMP, true, 0);
    insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note)
values (15, 'Javier', 'Pérez', 'javito', 'javier@hotmail.com', 1234, CURRENT_TIMESTAMP, false, 0);
insert into student (id, name, surname, username, email, password, entry_date, is_terminated, note)
values (6, 'Andrés', 'Rodriguez', 'andrew', 'andres@hotmail.com', 1234, CURRENT_TIMESTAMP, true, 0);