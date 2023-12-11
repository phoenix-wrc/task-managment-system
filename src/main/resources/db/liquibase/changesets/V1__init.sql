create table if not exists app_user
(
    id          bigserial primary key,
    name        varchar(255) not null,
    second_name varchar(255),
    last_name   varchar(255),
    username    varchar(255) not null unique,
    password    varchar(255) not null
);

create table if not exists task_priority
(
    id            bigserial primary key,
    priority_name varchar(255) not null unique
);

create table if not exists task
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar      null,
    status          varchar(255) not null,
    priority_id     bigint       null,
    expiration_date timestamp    null,
    create_date     timestamp DEFAULT Now(),
    constraint fk_task_task_priority foreign key (priority_id) references task_priority (id) on DELETE cascade on update no action
);

create table if not exists user_task_owner
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_user_task_user foreign key (user_id) references app_user (id) on delete cascade on update no action,
    constraint fk_user_task_task foreign key (task_id) references task (id) on delete cascade on update no action
);

create table if not exists user_task_executor
(
    task_id      bigint not null,
    user_id      bigint not null,
    date_appoint timestamp DEFAULT Now(),
    primary key (user_id, task_id),
    constraint fk_user_task_user foreign key (user_id) references app_user (id) on delete cascade on update no action,
    constraint fk_user_task_task foreign key (task_id) references task (id) on delete cascade on update no action
);

create table if not exists user_role
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_user_role_user foreign key (user_id) references app_user (id) on DELETE cascade on update no action
);