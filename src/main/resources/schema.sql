create schema if not exists task_management_system;

create table if not exists app_user

(
    id bigserial primary key,
    name varchar(255) not null,
    father_name varchar(255),
    lastname varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table if not exists task
(
    id bigserial primary key,
    title varchar(255) not null,
    description varchar(255) null,
    status varchar(255) not null,
    priority varchar(255) not null,
    create_date timestamp DEFAULT NOW(),
    expiration_date timestamp null
);


create table if not exists user_task
(
    user_id bigserial not null,
    task_id bigserial not null,
    primary key(user_id, task_id),
    constraint fk_app_user_task_app_user foreign key (user_id) references app_user(id) on delete cascade on update no action,
    constraint fk_app_user_task_task foreign key (task_id) references task(id) on delete cascade on update no action
);

create table if not exists user_role
(
    user_id bigint not null,
    role varchar(255) not null,
    primary key (user_id, role),
    constraint fk_app_user_user_role foreign key (user_id) references app_user(id) on delete cascade on update no action
)
