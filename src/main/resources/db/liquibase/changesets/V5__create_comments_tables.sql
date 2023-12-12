create table if not exists comment
(
    id bigserial primary key ,
    task_id bigint not null,
    user_id bigint not null,
    title varchar(255),
    description varchar,
    dateCreate date default now(),
    constraint fk_comment_user_comment foreign key (user_id) references app_user (id) on delete cascade on update no action,
    constraint fk_comment_task_comment foreign key (task_id) references task (id) on delete cascade on update no action
);
