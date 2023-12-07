insert into app_user (name, username, password)
values ('John Doe', 'johndoe@mail.ru', '$2a$10$yZC/FPekK9j9lkOucr60eOqjhwXxOn/9APcBaotGTdFgf/gO.zC0S'),
       ('John McClain', 'altcgeymervill@yandex.ru', '$2a$10$yZC/FPekK9j9lkOucr60eOqjhwXxOn/9APcBaotGTdFgf/gO.zC0S');

insert into task_priority (priority_name)
values ('HIGH'),
       ('MEDIUM'),
       ('LOW');

insert into task (title, description, status, priority_id, expiration_date)
values ('Купить подгузники', null, 'AWAITING', 2, '2023-12-06 13:00'),
       ('Сделать тестовое', 'вот сейчас делаю', 'IN_PROGRESS', 1, '2023-12-07 14:00'),
       ('Убраться', null, 'DONE', null, '2023-12-05 15:00'),
       ('Приготовить еду', 'Макарошки нельзя много есть', 'AWAITING', 3, '2023-12-04 16:00');

insert into user_task_owner(task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into user_role (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

insert into user_task_executor (task_id, user_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 2);