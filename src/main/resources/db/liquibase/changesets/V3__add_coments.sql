create table if not exists comment
(
    id          bigint,
    task_id     bigint,
    user_id   bigint,
    title       varchar(255),
    description varchar,
    date_create timestamp DEFAULT Now(),
    primary key (id, user_id, task_id),
    constraint fk_comment_task_user foreign key (user_id) references app_user(id) on delete cascade on update no action,
    constraint fk_comment_task_task foreign key (task_id) references task(id) on delete cascade on update no action
);
