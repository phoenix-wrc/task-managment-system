insert into app_user (name, lastname, username, password)

values ('John',  'Doe', 'johndoe@mail.ru', '$2a$10$2qSE.MSvjBnlrlpMF8PO4eA6//M06Ah4TGV62vp.HIqwRIJS4HnfS'),
       ('John', 'McClain', 'Alzheimerville@yandex.ru', '$2a$10$2qSE.MSvjBnlrlpMF8PO4eA6//M06Ah4TGV62vp.HIqwRIJS4HnfS');

-- insert into task_priority (priority_name)
-- values ('HIGH'),
--        ('MEDIUM'),
--        ('LOW');

insert into task (title, description, status,priority, expiration_date)
values ('Купить подгузники', null, 'AWAITING',  'MEDIUM' , '2024-12-06 13:00'),
       ('Сделать тестовое', 'вот сейчас делаю', 'IN_PROGRESS', 'HIGH', '2024-12-07 14:00'),
       ('Убраться', null, 'DONE',  'MEDIUM' , '2024-12-05 15:00'),
       ('Приготовить еду', 'Макарошки нельзя много есть', 'AWAITING', 'LOW', '2024-12-04 16:00');

insert into user_task(task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into user_role (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

-- insert into user_task_executor (task_id, user_id)
-- values (1, 1),
--        (2, 1),
--        (3, 2),
--        (4, 2);